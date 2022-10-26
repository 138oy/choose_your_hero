package practice.effective.chooseyourhero.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo

@OptIn(ExperimentalSnapperApi::class)
@Composable
internal fun HeroCard(
    item: Pair<String, Int>,
    itemIndex: Int,
    layoutInfo: LazyListSnapperLayoutInfo,
) {
    val modifier: Modifier = if (itemIndex == layoutInfo.currentItem?.index) Modifier
        .size(
            width = 350.dp,
            height = 525.dp
        )
        .padding(horizontal = 15.dp)

    else Modifier
        .size(width = 300.dp, height = 450.dp)
        .padding(horizontal = 15.dp)

    Card(modifier = modifier) {
        Image(
            painter = painterResource(item.second),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
        Box(contentAlignment = Alignment.BottomStart) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = item.first,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
            )
        }
    }
}
