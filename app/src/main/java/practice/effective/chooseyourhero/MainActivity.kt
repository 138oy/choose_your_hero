package practice.effective.chooseyourhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import practice.effective.chooseyourhero.navigation.ChoosingAHero
import practice.effective.chooseyourhero.ui.theme.ChooseYourHeroTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        FirebaseMessaging.getInstance().subscribeToTopic("all").addOnCompleteListener {}
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
