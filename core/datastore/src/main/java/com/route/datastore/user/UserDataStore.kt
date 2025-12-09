package com.route.datastore.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.route.datastore.UserRepo
import com.route.datastore.user.serializer.UserSerializer
import com.route.model.User
import kotlinx.coroutines.flow.Flow

class UserDataStore(context: Context) : UserRepo {
    private val Context.dataStore: DataStore<User> by dataStore(
        fileName = "user.json",
        serializer = UserSerializer()
    )
    private val dataStore = context.dataStore

    override val user: Flow<User> = dataStore.data
    override suspend fun setUser(user: User) {
        dataStore.updateData {
            it.copy(
                displayName = user.displayName,
                email = user.email,
                photoUrl = user.photoUrl
            )
        }
    }
}
