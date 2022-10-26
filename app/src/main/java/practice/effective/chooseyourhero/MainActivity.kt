package practice.effective.chooseyourhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import practice.effective.chooseyourhero.navigation.ChoosingAHero
import practice.effective.chooseyourhero.ui.theme.ChooseYourHeroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChooseYourHeroTheme {
                val appState = rememberAppState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
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
