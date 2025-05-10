package com.radlance.uikit.component.tabbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
    tabs: List<BottomTab>,
    routes: Sequence<String?>?,
    onTabClick: (BottomTab) -> Unit,
    modifier: Modifier = Modifier,
    bottomPadding: Dp
) {
    Column(modifier = modifier.background(CustomTheme.colors.white)) {
        Column(
            modifier = Modifier
                .height(67.dp)
                .offset(y = -bottomPadding)
                .background(CustomTheme.colors.white),
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalDivider(
                thickness = 1.dp, color = Color(0xFFA0A0A0).copy(alpha = 0.3f)
            )
            Spacer(Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                tabs.forEach { tab ->
                    val selected = routes?.any {
                        it == tab::class.qualifiedName
                    } ?: false

                    BottomTabItem(
                        bottomTab = tab,
                        selected = selected,
                        onTabClick = { if (!selected) onTabClick(it) }
                    )
                }
            }
        }
    }
}