package practice.effective.chooseyourhero.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeroDescription(
    name: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(MaterialTheme.colors.primary)
    ) {
        HeroName(name, modifier)

        Text(
            modifier = modifier,
            text = description,
            style = MaterialTheme.typography.body1,
        )
    }
}
