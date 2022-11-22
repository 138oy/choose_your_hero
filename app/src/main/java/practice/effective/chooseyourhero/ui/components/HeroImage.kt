package practice.effective.chooseyourhero.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import practice.effective.chooseyourhero.models.Hero

@Composable
fun HeroImage(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(
            LocalContext.current
        )
            .data(hero.imageUrl)
            .size(Size.ORIGINAL).build()
    )

    when (painter.state) {
        is AsyncImagePainter.State.Loading -> {
            ProgressCircle(modifier.size(40.dp))
        }
        is AsyncImagePainter.State.Error -> {
            Text(text = "Oops something went wrong. Try again!")
        }
        else -> {
            AsyncImage(
                model = painter.request.data,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
}
