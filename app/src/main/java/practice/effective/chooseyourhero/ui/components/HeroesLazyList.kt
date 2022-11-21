package practice.effective.chooseyourhero.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import practice.effective.chooseyourhero.ui.HeroUiState

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun HeroesLazyList(
    navController: NavHostController,
    lazyListState: LazyListState,
    layoutInfo: LazyListSnapperLayoutInfo,
    state: HeroUiState,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is HeroUiState.Loading -> {
            ProgressCircle(modifier.size(40.dp))
        }

        is HeroUiState.Empty -> {
            ProgressCircle(modifier.size(40.dp))
        }

        is HeroUiState.HeroesData -> {
            val items = state.heroesData
            LazyRow(
                modifier = modifier,
                state = lazyListState,
                flingBehavior = rememberSnapperFlingBehavior(lazyListState),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                items(items) { item ->
                    HeroCard(item, items.indexOf(item), layoutInfo, navController)
                }
            }
        }
    }
}