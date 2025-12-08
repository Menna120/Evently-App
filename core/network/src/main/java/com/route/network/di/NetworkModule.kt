package com.route.network.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.route.network.AuthRemoteRepo
import com.route.network.EventRemoteRepo
import com.route.network.UserRemoteRepo
import com.route.network.event.EventFireStoreRepo
import com.route.network.user.AuthFireStoreRepo
import com.route.network.user.UserFireStoreRepo
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.route.network")
class NetworkModule {

    @Single
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Single
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Single
    fun provideEventRemoteRepo(db: FirebaseFirestore): EventRemoteRepo = EventFireStoreRepo(db)

    @Single
    fun provideUserRemoteRepo(db: FirebaseFirestore): UserRemoteRepo = UserFireStoreRepo(db)

    @Single
    fun provideAuthRemoteRepo(auth: FirebaseAuth): AuthRemoteRepo = AuthFireStoreRepo(auth)
}
