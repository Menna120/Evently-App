package com.route.onboarding.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.route.designsystem.theme.EventlyTheme
import com.route.onboarding.R

@Composable
fun NavigationIconButton(
    @DrawableRes iconRes: Int,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scheme = MaterialTheme.colorScheme
    IconButton(
        onClick = onClick,
        modifier = modifier.border(
            width = 1.dp,
            color = scheme.primary,
            shape = CircleShape
        ),
        shape = CircleShape,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = scheme.background,
            contentColor = scheme.primary
        )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = contentDescription
        )
    }
}

@PreviewLightDark
@Composable
private fun PreviewNavigationIconButton() {
    EventlyTheme {
        NavigationIconButton(R.drawable.ic_arrow_left, "Previous", {})
    }
}
