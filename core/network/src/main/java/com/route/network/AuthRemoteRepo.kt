package com.route.network

interface AuthRemoteRepo {
    suspend fun signInWithCredential(idToken: String): String?
    suspend fun signInWithEmailAndPassword(email: String, password: String): String?
    suspend fun createUserWithEmailAndPassword(email: String, password: String): String?
    fun getToken(): String?
    fun signOut()
}
