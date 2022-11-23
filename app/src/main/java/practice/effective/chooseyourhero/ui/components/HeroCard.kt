package practice.effective.chooseyourhero.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import practice.effective.chooseyourhero.models.Hero
import practice.effective.chooseyourhero.navigation.HeroInfo

@OptIn(ExperimentalSnapperApi::class)
@Composable
internal fun HeroCard(
    hero: Hero,
    itemIndex: Int,
    layoutInfo: LazyListSnapperLayoutInfo,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints {
        val currHeight = (maxHeight / 12) * 10
        val currWidth = (currHeight / 3) * 2
        val otherHeight = (maxHeight / 12) * 9
        val otherWidth = (otherHeight / 3) * 2

        val cardModifier = if (itemIndex == layoutInfo.currentItem?.index) modifier.size(
            width = currWidth,
            height = currHeight
        )
        else modifier.size(width = otherWidth, height = otherHeight)

        Card(
            modifier = cardModifier
                .clickable(onClick = {
                    navController.navigate("${HeroInfo.route}/${hero.id}") {
                        popUpTo(
                            navController.graph.findStartDestination().id
                        ) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        ) {
            HeroImage(hero.imageUrl)
            Box(contentAlignment = Alignment.BottomStart) {
                Surface(
                    modifier = modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 10.dp), color = MaterialTheme.colors.primary
                )
                {
                    HeroName(hero.name, modifier.padding(10.dp))
                }
            }
        }
    }
}
