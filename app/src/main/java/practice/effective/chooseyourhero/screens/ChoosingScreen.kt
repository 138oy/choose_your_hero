package practice.effective.chooseyourhero.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
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
    heroesViewModel.getHeroesList(navController)
    val state = heroesViewModel.state.collectAsState()
    val lazyListState = rememberLazyListState()
    val layoutInfo: LazyListSnapperLayoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LogoWithSlogan()
        HeroesLazyList(
            navController,
            lazyListState,
            layoutInfo,
            state.value,
            modifier.fillMaxSize()
        )
    }
}