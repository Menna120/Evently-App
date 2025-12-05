package com.route.designsystem.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.route.designsystem.navigation.EventlyBottomItems
import com.route.designsystem.theme.EventlyTheme

@Composable
fun EventlyBottomNavBar(
    isSelected: (EventlyBottomItems) -> Boolean,
    onItemClick: (EventlyBottomItems) -> Unit
) {
    val scheme = MaterialTheme.colorScheme
    val bottomNavItems = listOf(
        EventlyBottomItems.Home,
        EventlyBottomItems.Map,
        null,
        EventlyBottomItems.Love,
        EventlyBottomItems.Profile
    )

    Surface(
        color = scheme.primary,
        contentColor = scheme.onPrimary,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            bottomNavItems.forEach { destination ->
                if (destination == null) {
                    Spacer(modifier = Modifier.weight(1f))
                } else {
                    val selected = isSelected(destination)
                    val iconId =
                        if (selected) destination.selectedIconId else destination.unselectedIconId

                    NavigationBarItem(
                        selected = selected,
                        onClick = { onItemClick(destination) },
                        modifier = Modifier.weight(1f),
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = iconId),
                                contentDescription = stringResource(id = destination.labelId)
                            )
                        },
                        label = { Text(text = stringResource(id = destination.labelId)) },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent,
                            unselectedTextColor = scheme.onPrimary,
                            selectedTextColor = scheme.onPrimary,
                            unselectedIconColor = scheme.onPrimary,
                            selectedIconColor = scheme.onPrimary,
                        )
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun EventlyBottomNavBarPreview() {
    EventlyTheme {
        EventlyBottomNavBar(
            isSelected = { it == EventlyBottomItems.Home },
            onItemClick = {}
        )
    }
}
