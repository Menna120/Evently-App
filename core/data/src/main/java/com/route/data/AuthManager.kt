package com.route.data

import android.content.Context
import com.route.model.Result
import com.route.model.User
import com.route.network.AuthRemoteRepo
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class AuthManager(private val repo: AuthRemoteRepo) {
    fun signInWithGoogle(context: Context): Flow<Result<User>> =
        repo.signInWithGoogle(context)

    fun signInWithEmail(email: String, password: String): Flow<Result<User>> =
        repo.signInWithEmail(email, password)

    fun signUpWithEmail(email: String, password: String): Flow<Result<User>> =
        repo.signUpWithEmail(email, password)

    fun getAuthState(): Flow<User?> = repo.getAuthState()

    val currentUser: User? = repo.currentUser

    suspend fun signOut() = repo.signOut()
}
