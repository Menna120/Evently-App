package com.route.onboarding.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.route.onboarding.R

@Immutable
enum class OnboardingPage(
    @param:StringRes val titleResId: Int,
    @param:StringRes val descriptionResId: Int,
    @param:DrawableRes val illustrationResId: Int,
) {
    Welcome(
        titleResId = R.string.onboarding_welcome_title,
        descriptionResId = R.string.onboarding_welcome_description,
        illustrationResId = R.drawable.ic_being_creative
    ),
    Discover(
        titleResId = R.string.onboarding_discover_title,
        descriptionResId = R.string.onboarding_discover_description,
        illustrationResId = R.drawable.ic_hot_trending
    ),
    Plan(
        titleResId = R.string.onboarding_plan_title,
        descriptionResId = R.string.onboarding_plan_description,
        illustrationResId = R.drawable.ic_event_planning
    ),
    Connect(
        titleResId = R.string.onboarding_connect_title,
        descriptionResId = R.string.onboarding_connect_description,
        illustrationResId = R.drawable.ic_social_media
    )
}
