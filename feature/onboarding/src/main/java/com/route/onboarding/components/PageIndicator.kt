package com.route.onboarding.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.route.designsystem.theme.EventlyTheme

@Composable
fun PageIndicator(
    currentPage: Int,
    pageCount: Int,
    modifier: Modifier = Modifier
) {
    val scheme = MaterialTheme.colorScheme
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage

            val currentWidth by animateDpAsState(
                targetValue = if (isSelected) 24.dp else 8.dp,
                animationSpec = tween(durationMillis = 300),
                label = "indicatorWidthAnimation"
            )

            val currentColor by animateColorAsState(
                if (index == currentPage) scheme.primary else scheme.onBackground,
                animationSpec = tween(durationMillis = 300),
                label = "indicatorColorAnimation"
            )

            Box(
                modifier = Modifier
                    .size(currentWidth, 8.dp)
                    .clip(CircleShape)
                    .background(currentColor)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewPageIndicator() {
    EventlyTheme {
        PageIndicator(0, 3)
    }
}
