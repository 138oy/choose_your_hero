package practice.effective.chooseyourhero.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

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
    val deepLinks = listOf(navDeepLink { uriPattern = "https://app.com/{hero_id}" })
}

object ErrorMessage : AppDestination {
    override val route = "error"
}
