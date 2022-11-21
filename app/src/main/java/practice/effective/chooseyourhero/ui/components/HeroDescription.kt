package practice.effective.chooseyourhero.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HeroDescription(
    name: String,
    description: String,
    modifier: Modifier = Modifier
) {
    HeroName(name, modifier)

    Text(
        modifier = modifier,
        text = description,
        style = MaterialTheme.typography.body1,
    )
}