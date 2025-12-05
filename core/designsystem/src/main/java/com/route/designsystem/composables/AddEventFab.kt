package com.route.designsystem.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.route.designsystem.R
import com.route.designsystem.theme.EventlyTheme

@Composable
fun AddEventFab(modifier: Modifier = Modifier, onClick: () -> Unit) {
    val scheme = MaterialTheme.colorScheme
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.border(5.dp, scheme.onPrimary, CircleShape),
        shape = CircleShape,
        containerColor = scheme.primary,
        contentColor = scheme.onPrimary
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_plus),
            contentDescription = stringResource(R.string.add_event)
        )
    }
}

@PreviewLightDark
@Composable
fun AddEventFabPreview() {
    EventlyTheme {
        AddEventFab(onClick = {})
    }
}
