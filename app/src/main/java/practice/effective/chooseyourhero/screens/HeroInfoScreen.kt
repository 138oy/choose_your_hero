package practice.effective.chooseyourhero.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import practice.effective.chooseyourhero.viewmodels.HeroesViewModel

@Composable
internal fun HeroInfoScreen(onBackClick: () -> Unit = {}, heroId: String?) {
    val heroesViewModel: HeroesViewModel = viewModel()
    val hero = remember(heroId) { heroesViewModel.getHeroesList().find { it.id == heroId } }
    Card(modifier = Modifier.fillMaxSize()) {
        if (hero != null) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(
                    LocalContext.current
                )
                    .data(hero.imageUrl)
                    .size(Size.ORIGINAL).build()
            )

            when (painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    Box {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.Center)
                        )
                    }
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

        Box {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "", tint = Color.White)
            }
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = hero?.name ?: "error",
                    style = MaterialTheme.typography.h2,
                )
                Text(
                    text = hero?.description ?: "error",
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}
