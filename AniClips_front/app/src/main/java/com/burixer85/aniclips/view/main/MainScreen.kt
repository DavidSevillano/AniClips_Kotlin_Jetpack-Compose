package com.burixer85.aniclips.view.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.burixer85.aniclips.R
import com.burixer85.aniclips.view.core.components.AniNavigationBarItem
import com.burixer85.aniclips.view.main.home.HomeScreen
import com.burixer85.aniclips.view.main.model.NavItem
import com.burixer85.aniclips.view.main.profile.ProfileScreen
import com.burixer85.aniclips.view.main.search.SearchScreen

@Composable
fun MainScreen() {
    var itemList = listOf(
        NavItem("Home", R.drawable.ic_home),
        NavItem("Search", R.drawable.ic_search),
        NavItem("Profile", R.drawable.ic_profile)
    )


    var selectedIndex by remember { mutableIntStateOf(0) }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.background
            ) {
                itemList.forEachIndexed { index, item ->
                    AniNavigationBarItem(navItem = item, isSelected = index == selectedIndex) {
                        selectedIndex = index
                    }
                }
            }
        }
    ) { innerPadding ->
        when (selectedIndex) {
            0 -> HomeScreen(Modifier.padding(innerPadding))
            1 -> SearchScreen(Modifier.padding(innerPadding))
            2 -> ProfileScreen(Modifier.padding(innerPadding))
        }
    }
}