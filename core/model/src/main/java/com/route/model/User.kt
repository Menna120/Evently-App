package com.route.model

import java.util.Date

data class User(
    val id: String,
    val name: String,
    val email: String,
    val photoUrl: String,
    val createdAt: Date,
    val eventsList: List<String>
)
