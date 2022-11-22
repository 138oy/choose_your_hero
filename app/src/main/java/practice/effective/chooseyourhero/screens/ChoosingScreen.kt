package practice.effective.chooseyourhero.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import practice.effective.chooseyourhero.ui.HeroUiState
import practice.effective.chooseyourhero.ui.components.HeroesLazyList
import practice.effective.chooseyourhero.ui.components.LogoWithSlogan
import practice.effective.chooseyourhero.viewmodels.HeroesViewModel

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun ChoosingScreen(
    navController: NavHostController,
    heroesViewModel: HeroesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    var orientation by remember { mutableStateOf(Configuration.ORIENTATION_PORTRAIT) }
    val configuration = LocalConfiguration.current

    LaunchedEffect(configuration) {
        snapshotFlow { configuration.orientation }
            .collect { orientation = it }
    }

    heroesViewModel.getHeroesList(navController)
    val state = heroesViewModel.state.collectAsState()
    val lazyListState = rememberLazyListState()
    val layoutInfo: LazyListSnapperLayoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)

    when (orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            ChoosingScreenLandscape(navController, lazyListState, layoutInfo, state)
        }
        else -> {
            ChoosingScreenPortrait(navController, lazyListState, layoutInfo, state)
        }
    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun ChoosingScreenPortrait(
    navController: NavController,
    lazyListState: LazyListState,
    layoutInfo: LazyListSnapperLayoutInfo,
    state: State<HeroUiState>,
    modifier: Modifier = Modifier,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LogoWithSlogan(
            modifier
                .padding(vertical = 5.dp)
                .size(150.dp)
        )
        HeroesLazyList(
            navController,
            lazyListState,
            layoutInfo,
            state.value,
            modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun ChoosingScreenLandscape(
    navController: NavController,
    lazyListState: LazyListState,
    layoutInfo: LazyListSnapperLayoutInfo,
    state: State<HeroUiState>,
    modifier: Modifier = Modifier,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.weight(2f)
        ) {
            LogoWithSlogan(
                modifier
                    .padding(horizontal = 5.dp)
                    .size(150.dp)
            )
        }
        Box(modifier = modifier.weight(3f)) {
            HeroesLazyList(
                navController,
                lazyListState,
                layoutInfo,
                state.value,
                modifier.fillMaxSize()
            )
        }
    }
}
