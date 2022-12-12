package practice.effective.chooseyourhero.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import practice.effective.chooseyourhero.models.Hero
import practice.effective.chooseyourhero.ui.HeroUiState
import practice.effective.chooseyourhero.ui.components.HeroDescription
import practice.effective.chooseyourhero.ui.components.HeroImage
import practice.effective.chooseyourhero.ui.components.ProgressCircle
import practice.effective.chooseyourhero.ui.getOrientationState
import practice.effective.chooseyourhero.viewmodels.HeroesViewModel

@Composable
internal fun HeroInfoScreen(
    navController: NavController,
    onBackClick: () -> Unit,
    heroId: String?,
    heroesViewModel: HeroesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val orientation = getOrientationState()

    heroesViewModel.getHero(heroId!!, navController)
    val state = heroesViewModel.state.collectAsState()
    HeroInfoScreen(orientation, onBackClick, state.value, modifier)
}

@Composable
internal fun HeroInfoScreen(
    orientation: Int,
    onBackClick: () -> Unit,
    state: HeroUiState,
    modifier: Modifier = Modifier
) {
    when (state) {
        is HeroUiState.Loading -> {
            ProgressCircle(modifier.size(40.dp))
        }

        is HeroUiState.Empty -> {
            ProgressCircle(modifier.size(40.dp))
        }

        is HeroUiState.HeroesData -> {
            when (orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    HeroInfoScreenLandscape(onBackClick, state.heroesData.single())
                }
                else -> {
                    HeroInfoScreenPortrait(onBackClick, state.heroesData.single())
                }
            }
        }
    }
}


@Composable
internal fun HeroInfoScreenPortrait(
    onBackClick: () -> Unit,
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxSize()) {
        HeroImage(hero.imageUrl)
        Box {
            IconButton(
                onClick = onBackClick,
                modifier = modifier.padding(vertical = 15.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "", tint = Color.White)
            }

            Box(
                modifier = modifier
                    .align(Alignment.BottomStart)
                    .padding(15.dp)
                    .background(MaterialTheme.colors.primary)
                    .fillMaxWidth()
            ) {
                HeroDescription(hero.name, hero.description, modifier.padding(5.dp))
            }
        }
    }
}

@Composable
internal fun HeroInfoScreenLandscape(
    onBackClick: () -> Unit,
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Row {
        Box(
            modifier = modifier.weight(2f)
        ) {
            Card(modifier = modifier.fillMaxSize()) {
                HeroImage(hero.imageUrl)
                Box {
                    IconButton(
                        onClick = onBackClick,
                        modifier = modifier.padding(vertical = 15.dp)
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "", tint = Color.White)
                    }
                }
            }
        }
        Box(
            modifier = modifier
                .weight(3f)
                .padding(15.dp)
        ) {
            HeroDescription(hero.name, hero.description, modifier.padding(5.dp))
        }
    }
}
