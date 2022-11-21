package practice.effective.chooseyourhero.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import practice.effective.chooseyourhero.R

@Composable
fun LogoWithSlogan(
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.marvel),
            contentDescription = "",
            modifier = modifier
                .padding(vertical = 5.dp)
                .size(150.dp),
        )
        Text(
            text = stringResource(id = R.string.choose_your_hero),
            style = MaterialTheme.typography.h1,
        )
    }
}