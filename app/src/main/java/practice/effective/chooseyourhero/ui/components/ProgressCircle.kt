package practice.effective.chooseyourhero.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProgressCircle(modifier: Modifier = Modifier) {
    Box {
        CircularProgressIndicator(
            modifier = modifier
                .align(Alignment.Center)
        )
    }
}
