package com.route.data

import com.route.network.AuthRemoteRepo
import org.koin.core.annotation.Single

@Single
class AuthManager(private val repo: AuthRemoteRepo) {
    suspend fun signInWithCredential(idToken: String): String? =
        repo.signInWithCredential(idToken)

    suspend fun signInWithEmailAndPassword(email: String, password: String): String? =
        repo.signInWithEmailAndPassword(email, password)

    suspend fun createUserWithEmailAndPassword(email: String, password: String): String? =
        repo.createUserWithEmailAndPassword(email, password)

    fun getToken(): String? = repo.getToken()

    fun signOut() = repo.signOut()
}
