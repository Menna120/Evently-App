package com.route.evently.navigation.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.route.evently.R
import kotlinx.serialization.Serializable

@Serializable
sealed interface MainDestination {
    @Serializable
    object CreateEventDestination : MainDestination

    @Serializable
    object EventDetailsDestination : MainDestination

    @Serializable
    object UpdateEventDestination : MainDestination

    @Serializable
    object BottomNavDestination : MainDestination

    @Serializable
    sealed class BottomItemDestination(
        @param:StringRes val labelId: Int,
        @param:DrawableRes val unselectedIconId: Int,
        @param:DrawableRes val selectedIconId: Int
    ) : MainDestination {
        @Serializable
        data object Home :
            BottomItemDestination(
                R.string.home,
                R.drawable.ic_outlined_home,
                R.drawable.ic_filled_home
            )

        @Serializable
        data object Map :
            BottomItemDestination(
                R.string.map,
                R.drawable.ic_outlined_map_pin,
                R.drawable.ic_filled_map_pin
            )

        @Serializable
        data object Love :
            BottomItemDestination(
                R.string.love,
                R.drawable.ic_outlined_heart,
                R.drawable.ic_filled_heart
            )

        @Serializable
        data object Profile :
            BottomItemDestination(
                R.string.profile,
                R.drawable.ic_outlined_user,
                R.drawable.ic_filled_user
            )
    }
}
