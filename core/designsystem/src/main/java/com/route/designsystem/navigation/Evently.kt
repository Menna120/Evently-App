package com.route.designsystem.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.route.designsystem.R
import kotlinx.serialization.Serializable

@Serializable
object Evently

@Serializable
object CreateEventDestination

@Serializable
object EventDetailsDestination

@Serializable
object UpdateEventDestination

@Serializable
sealed class EventlyBottomItems(
    @param:StringRes val labelId: Int,
    @param:DrawableRes val unselectedIconId: Int,
    @param:DrawableRes val selectedIconId: Int
) {
    @Serializable
    data object Home :
        EventlyBottomItems(
            R.string.home,
            R.drawable.ic_outlined_home,
            R.drawable.ic_filled_home
        )

    @Serializable
    data object Map :
        EventlyBottomItems(
            R.string.map,
            R.drawable.ic_outlined_map_pin,
            R.drawable.ic_filled_map_pin
        )

    @Serializable
    data object Love :
        EventlyBottomItems(
            R.string.love,
            R.drawable.ic_outlined_heart,
            R.drawable.ic_filled_heart
        )

    @Serializable
    data object Profile :
        EventlyBottomItems(
            R.string.profile,
            R.drawable.ic_outlined_user,
            R.drawable.ic_filled_user
        )
}
