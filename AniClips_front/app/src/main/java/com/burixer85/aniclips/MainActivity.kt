package com.burixer85.aniclips

import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.burixer85.aniclips.ui.theme.AniClipsTheme
import com.burixer85.aniclips.view.core.navigation.NavigationWrapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AniClipsTheme {
                NavigationWrapper { isMainScreen ->
                    val controller = window.insetsController
                    if (isMainScreen) {
                        controller?.hide(WindowInsets.Type.navigationBars())
                        controller?.hide(WindowInsets.Type.statusBars())
                        controller?.systemBarsBehavior =
                            WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    } else {
                        controller?.show(WindowInsets.Type.navigationBars())
                        controller?.show(WindowInsets.Type.statusBars())
                    }
                }
            }
        }
    }
}