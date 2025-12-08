package com.route.network.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.route.network.AuthRemoteRepo
import kotlinx.coroutines.tasks.await

class AuthFireStoreRepo(private val firebaseAuth: FirebaseAuth) : AuthRemoteRepo {

    override suspend fun signInWithCredential(idToken: String): String? =
        firebaseAuth.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
            .await().user?.uid

    override suspend fun signInWithEmailAndPassword(email: String, password: String): String? =
        firebaseAuth.signInWithEmailAndPassword(email, password).await().user?.uid

    override suspend fun createUserWithEmailAndPassword(email: String, password: String): String? =
        firebaseAuth.createUserWithEmailAndPassword(email, password).await().user?.uid

    override fun getToken(): String? = firebaseAuth.currentUser?.uid

    override fun signOut() = firebaseAuth.signOut()
}
