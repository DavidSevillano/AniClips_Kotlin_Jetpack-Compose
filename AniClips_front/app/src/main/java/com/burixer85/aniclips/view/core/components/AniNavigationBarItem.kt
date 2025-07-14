package com.burixer85.aniclips.view.core.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.burixer85.aniclips.ui.theme.AniClipsLighterBlue
import com.burixer85.aniclips.view.main.model.NavItem

@Composable
fun RowScope.AniNavigationBarItem(
    navItem: NavItem, isSelected: Boolean, onItemClick: () -> Unit
) {
    NavigationBarItem(
        selected = isSelected,
        onClick = onItemClick,
        icon = { Icon(painterResource(navItem.icon), navItem.name) },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = AniClipsLighterBlue,
        )
    )
}