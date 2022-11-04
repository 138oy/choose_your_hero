package practice.effective.chooseyourhero

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import practice.effective.chooseyourhero.navigation.ChoosingAHero
import practice.effective.chooseyourhero.navigation.HeroInfo
import practice.effective.chooseyourhero.screens.ChoosingScreen
import practice.effective.chooseyourhero.screens.HeroInfoScreen

@Composable
fun ChooseYourHeroNavHost(
    navController: NavHostController,
    startDestination: String,
    appState: AppState
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = ChoosingAHero.route) {
            ChoosingScreen(navController)
        }
        composable(
            route = "${HeroInfo.route}/{${HeroInfo.heroIdArg}}",
            arguments = HeroInfo.arguments,
        ) { navBackStackEntry ->
            val heroId = navBackStackEntry.arguments?.getString(HeroInfo.heroIdArg)
            HeroInfoScreen(onBackClick = { appState.onGoBack() }, heroId)
        }
    }
}
