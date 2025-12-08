package com.route.datastore.settings.model

import kotlinx.serialization.Serializable

@Serializable
data class Settings(
    val showOnboarding: Boolean = true,
    val isDark: Boolean = false,
    val language: String = "en"
)
