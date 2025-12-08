package com.route.network.user

import com.google.firebase.firestore.FirebaseFirestore
import com.route.model.User
import com.route.network.UserRemoteRepo
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class UserFireStoreRepo(db: FirebaseFirestore) : UserRemoteRepo {
    private val userCollection = db.collection("event_users")

    override suspend fun getUser(userId: String): User? =
        userCollection.document(userId).get().await().toObject(User::class.java)

    override suspend fun addUser(user: User) {
        userCollection.add(user).await()
    }

    override suspend fun updateUser(user: User) {
        userCollection.document(user.id).set(user).await()
    }

    override suspend fun deleteUser(user: User) {
        userCollection.document(user.id).delete().await()
    }
}
