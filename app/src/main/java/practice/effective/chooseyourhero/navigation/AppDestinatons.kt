package practice.effective.chooseyourhero.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface AppDestination {
    val route: String
}

object ChoosingAHero : AppDestination {
    override val route = "choosing_a_hero"

}

object HeroInfo : AppDestination {
    override val route = "hero_info"
    const val heroIdArg = "hero_id"
    val arguments = listOf(
        navArgument(heroIdArg) { type = NavType.StringType }
    )
}