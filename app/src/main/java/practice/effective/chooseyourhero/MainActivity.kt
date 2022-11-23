package practice.effective.chooseyourhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import practice.effective.chooseyourhero.navigation.ChoosingAHero
import practice.effective.chooseyourhero.ui.theme.ChooseYourHeroTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ChooseYourHeroTheme {
                val appState = rememberAppState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ChooseYourHeroNavHost(
                        navController = appState.navController,
                        startDestination = ChoosingAHero.route,
                        appState
                    )
                }
            }
        }
    }
}
