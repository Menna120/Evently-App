package com.route.event.event_details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EventDetailsScreen(
    modifier: Modifier = Modifier
) {
    Text(text = "EventDetailsScreen")
}

@Preview
@Composable
private fun PreviewEventDetailsScreen() {
    EventDetailsScreen()
}
