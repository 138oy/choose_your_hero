package practice.effective.chooseyourhero.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import practice.effective.chooseyourhero.models.Hero

@OptIn(ExperimentalSnapperApi::class)
@Composable
internal fun HeroCard(
    hero: Hero,
    itemIndex: Int,
    layoutInfo: LazyListSnapperLayoutInfo,
    onHeroClick: (String) -> Unit = {},
) {
    val modifier: Modifier = if (itemIndex == layoutInfo.currentItem?.index)
        Modifier.size(width = 350.dp, height = 525.dp)
    else Modifier.size(width = 300.dp, height = 450.dp)

    Card(modifier = modifier.clickable(onClick = { onHeroClick(hero.id) })) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(
                LocalContext.current
            )
                .data(hero.imageUrl)
                .size(Size.ORIGINAL).build()
        )

        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator()
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

        Box(contentAlignment = Alignment.BottomStart) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = hero.name,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
            )
        }
    }
}
