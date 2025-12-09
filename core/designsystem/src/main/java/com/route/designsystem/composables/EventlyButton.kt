package com.route.designsystem.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.designsystem.theme.EventlyTheme

@Composable
fun EventlyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scheme = MaterialTheme.colorScheme
    val shape = MaterialTheme.shapes
    val type = MaterialTheme.typography

    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = shape.medium,
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = scheme.primary,
            contentColor = scheme.onPrimary
        )
    ) {
        Text(text, style = type.titleLarge)
    }
}

@Preview
@Composable
fun EventlyButtonPreview() {
    EventlyTheme {
        EventlyButton(text = "Let’s Start", onClick = {})
    }
}
