package com.route.network

import android.content.Context
import com.route.model.Result
import com.route.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRemoteRepo {
    val currentUser: User?
    fun getAuthState(): Flow<User?>
    fun signInWithGoogle(context: Context): Flow<Result<User>>
    fun signInWithEmail(email: String, pass: String): Flow<Result<User>>
    fun signUpWithEmail(email: String, pass: String): Flow<Result<User>>
    suspend fun signOut()
}
