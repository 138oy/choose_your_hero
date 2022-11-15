package practice.effective.chooseyourhero.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun ErrorScreen(
    modifier: Modifier = Modifier
) {
    Box {
        Text(
            text = "Oops something went wrong. Try again later!",
            style = MaterialTheme.typography.h2,
            modifier = modifier.align(Alignment.Center),
        )
    }
}
