package com.burixer85.aniclips

import android.os.Bundle
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
                NavigationWrapper()
            }
        }
    }
}
