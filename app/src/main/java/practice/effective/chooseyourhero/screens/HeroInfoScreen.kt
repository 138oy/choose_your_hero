package practice.effective.chooseyourhero.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
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
import practice.effective.chooseyourhero.models.Hero
import practice.effective.chooseyourhero.viewmodels.HeroesViewModel

val heroData: MutableList<Hero> = mutableStateListOf(Hero("", "", "", ""))

@Composable
internal fun HeroInfoScreen(
    onBackClick: () -> Unit = {},
    heroId: String?,
    heroesViewModel: HeroesViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
//    val isFetched = false
//    val hero = remember(heroId) { heroesViewModel.getHero(heroId!!) }
    heroesViewModel.getHero(heroId!!)
    LaunchedEffect(Unit) {
        heroesViewModel.singleHero.value.collect { elem ->
            heroData[0] = elem
        }
    }
    Card(modifier = modifier.fillMaxSize()) {
        val hero = heroData.single()
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
                        modifier = modifier
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

        Box {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "", tint = Color.White)
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .align(Alignment.BottomStart)
                    .background(MaterialTheme.colors.primary)
            ) {
                Text(
                    modifier = modifier.padding(5.dp),
                    text = hero.name,
                    style = MaterialTheme.typography.h2,
                )

                Text(
                    modifier = modifier.padding(5.dp),
                    text = hero.description,
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}
