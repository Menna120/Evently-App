package com.route.network.auth

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.route.model.Result
import com.route.model.User
import com.route.network.AuthRemoteRepo
import com.route.network.BuildConfig
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.UUID

class AuthFireStoreRepo(private val auth: FirebaseAuth) : AuthRemoteRepo {

    override val currentUser: User?
        get() = auth.currentUser?.toUser()

    override fun getAuthState(): Flow<User?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser?.toUser())
        }
        auth.addAuthStateListener(listener)
        awaitClose { auth.removeAuthStateListener(listener) }
    }

    override fun signInWithGoogle(context: Context): Flow<Result<User>> = flow {
        emit(Result.Loading)
        try {
            val credentialManager = CredentialManager.create(context)
            val hashedNonce = UUID.randomUUID().toString()

            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(BuildConfig.WEB_CLIENT_ID)
                .setNonce(hashedNonce)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(context, request)
            val user = handleSignIn(result)
            emit(Result.Success(user))
        } catch (e: GetCredentialException) {
            emit(Result.Error(e.message ?: "Google Sign In Failed"))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown Error"))
        }
    }

    private suspend fun handleSignIn(result: GetCredentialResponse): User {
        val credential = result.credential
        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {

            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            val authCredential = GoogleAuthProvider.getCredential(
                googleIdTokenCredential.idToken, null
            )

            val authResult = auth.signInWithCredential(authCredential).await()
            return authResult.user?.toUser() ?: throw Exception("User extraction failed")
        }
        throw Exception("Invalid Credential Type")
    }

    override fun signInWithEmail(email: String, pass: String): Flow<Result<User>> = flow {
        emit(Result.Loading)
        try {
            val result = auth.signInWithEmailAndPassword(email, pass).await()
            result.user?.toUser()?.let { emit(Result.Success(it)) }
                ?: emit(Result.Error("User not found"))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Login failed"))
        }
    }

    override fun signUpWithEmail(email: String, pass: String): Flow<Result<User>> = flow {
        emit(Result.Loading)
        try {
            val result = auth.createUserWithEmailAndPassword(email, pass).await()
            result.user?.toUser()?.let { emit(Result.Success(it)) }
                ?: emit(Result.Error("Registration failed"))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Sign up failed"))
        }
    }

    override suspend fun signOut() = auth.signOut()

    private fun FirebaseUser.toUser(): User {
        return User(
            id = this.uid,
            email = this.email,
            displayName = this.displayName,
            photoUrl = this.photoUrl?.toString()
        )
    }
}
