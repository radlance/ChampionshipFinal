package com.radlance.uikit.component.tabbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.radlance.uikit.theme.CustomTheme

@Composable
fun BottomTabBar(
    selectedTab: BottomTab,
    onTabClick: (BottomTab) -> Unit,
    modifier: Modifier = Modifier,
    bottomPadding: Dp
) {
    Column(modifier = modifier.background(CustomTheme.colors.white)) {
        Column(
            modifier = Modifier
                .height(59.dp)
                .offset(y = -bottomPadding)
                .background(CustomTheme.colors.white),
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalDivider(
                thickness = 1.dp, color = Color(
                    red = 160,
                    green = 160,
                    blue = 160,
                    alpha = (0.3f * 255).toInt()
                )
            )
            Spacer(Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val tabs =
                    listOf(BottomTab.Home, BottomTab.Catalog, BottomTab.Projects, BottomTab.Profile)
                tabs.forEach { tab ->
                    BottomTabItem(
                        bottomTab = tab,
                        selected = tab == selectedTab,
                        onTabClick = onTabClick
                    )
                }
            }
        }
    }
}