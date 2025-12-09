package com.route.network.util

import android.util.Log
import com.route.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MakeFirebaseCall {
    companion object {
        fun <T> safeCall(block: suspend () -> T): Flow<Result<T>> = flow {
            emit(Result.Loading)
            try {
                val response = block()
                emit(Result.Success(response))
            } catch (e: Exception) {
                Log.e("FirebaseCall", "Error making Firebase call", e)
                emit(Result.Error(e.message ?: "Unknown Error"))
            }
        }
    }
}
