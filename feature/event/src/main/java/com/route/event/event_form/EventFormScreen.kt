package com.route.event.event_form

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EventFormScreen(
    modifier: Modifier = Modifier
) {
    Text(text = "EventForm")
}

@Preview
@Composable
private fun PreviewEventFormScreen() {
    EventFormScreen()
}
