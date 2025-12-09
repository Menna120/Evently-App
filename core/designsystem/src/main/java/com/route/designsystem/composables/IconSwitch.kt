package com.route.designsystem.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.route.designsystem.R
import com.route.designsystem.theme.EventlyTheme

@Immutable
private data class IconSwitchColors(
    val selectedIconColor: Color,
    val unselectedIconColor: Color,
    val thumbColor: Color,
    val trackColor: Color,
    val borderColor: Color
)

private object IconSwitchDefaults {
    @Composable
    fun colors(
        selectedIconColor: Color = MaterialTheme.colorScheme.onPrimary,
        unselectedIconColor: Color = MaterialTheme.colorScheme.primary,
        thumbColor: Color = MaterialTheme.colorScheme.primary,
        trackColor: Color = MaterialTheme.colorScheme.background,
        borderColor: Color = MaterialTheme.colorScheme.primary
    ): IconSwitchColors = IconSwitchColors(
        selectedIconColor = selectedIconColor,
        unselectedIconColor = unselectedIconColor,
        thumbColor = thumbColor,
        trackColor = trackColor,
        borderColor = borderColor
    )
}

@Composable
private fun IconSwitch(
    checked: Boolean,
    onIcon: Int,
    offIcon: Int,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    colors: IconSwitchColors = IconSwitchDefaults.colors(),
    width: Dp = 72.dp,
    height: Dp = 32.dp,
    thumbPadding: Dp = 4.dp
) {
    val cornerRadius = height / 2

    val interactionSource = remember { MutableInteractionSource() }

    val animateOffset by animateDpAsState(
        targetValue = if (checked) width - height else 0.dp,
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
        label = "thumbOffset"
    )

    @Composable
    fun IconComponent(iconResId: Int, tint: Color) {
        val iconSize = height - (thumbPadding * 2)

        Icon(
            imageVector = ImageVector.vectorResource(iconResId),
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(iconSize)
        )
    }

    Box(
        modifier = modifier
            .size(width, height)
            .clip(RoundedCornerShape(cornerRadius))
            .background(colors.trackColor)
            .border(2.dp, colors.borderColor, RoundedCornerShape(cornerRadius))
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onCheckedChange(!checked)
            }
    ) {
        // ----------------------------------------------------------------
        // TRACK CONTENT (Inactive Icons on the background)
        // ----------------------------------------------------------------
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left Content (ON Icon at OFF position) - Visible only when Thumb is on the right (checked)
            Row(
                modifier = Modifier
                    .size(height)
                    .padding(thumbPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(
                    visible = checked,
                    enter = fadeIn(tween(300)),
                    exit = fadeOut(tween(200))
                ) {
                    IconComponent(onIcon, colors.unselectedIconColor)
                }
            }

            // Right Content (OFF Icon at ON position) - Visible only when Thumb is on the left (!checked)
            Row(
                modifier = Modifier
                    .size(height)
                    .padding(thumbPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(
                    visible = !checked,
                    enter = fadeIn(tween(300)),
                    exit = fadeOut(tween(200))
                ) {
                    IconComponent(offIcon, colors.unselectedIconColor)
                }
            }
        }

        // ----------------------------------------------------------------
        // THUMB (The Sliding Circle)
        // ----------------------------------------------------------------
        Box(
            modifier = Modifier
                .offset(x = animateOffset)
                .size(height)
                .clip(CircleShape)
                .background(colors.thumbColor),
            contentAlignment = Alignment.Center
        ) {
            // Crossfade transitions the icon inside the thumb
            AnimatedContent(
                targetState = checked,
                transitionSpec = {
                    fadeIn(
                        animationSpec = tween(300)
                    ) togetherWith fadeOut(
                        animationSpec = tween(300)
                    )
                },
                label = "thumbContent"
            ) { isChecked ->
                if (isChecked) {
                    IconComponent(offIcon, colors.selectedIconColor)
                } else {
                    IconComponent(onIcon, colors.selectedIconColor)
                }
            }
        }
    }
}

@Composable
fun ThemeSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    IconSwitch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        onIcon = R.drawable.ic_sun,
        offIcon = R.drawable.ic_moon
    )
}

@Composable
fun LanguageSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    IconSwitch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        onIcon = R.drawable.ic_us,
        offIcon = R.drawable.ic_eg,
        colors = IconSwitchDefaults.colors(
            selectedIconColor = Color.Unspecified,
            unselectedIconColor = Color.Unspecified
        )
    )
}

@Preview
@Composable
private fun LanguageSwitchPreview() {
    val (isUS, setUS) = remember { mutableStateOf(false) }

    EventlyTheme {
        LanguageSwitch(
            checked = isUS,
            onCheckedChange = setUS
        )
    }
}

@Preview
@Composable
private fun ThemeSwitchPreview() {
    val (isDay, setDay) = remember { mutableStateOf(false) }

    EventlyTheme {
        ThemeSwitch(
            checked = isDay,
            onCheckedChange = setDay
        )
    }
}
