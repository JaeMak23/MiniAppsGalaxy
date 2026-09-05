package com.jaemak23.miniappsgalaxy.core.ui.components.kfab

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

@Composable
fun KFabList(
    items: List<SpeedDialItem>,
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    onExpandedChange: (Boolean) -> Unit,
) {
    NavigationBackHandler(
        state = rememberNavigationEventState(NavigationEventInfo.None),
        isBackEnabled = expanded,
        onBackCompleted = { onExpandedChange(false) },
        onBackCancelled = { }
    )

    Column(
        horizontalAlignment = Alignment.End,
        modifier = modifier
    ) {

        items.forEach { item ->
            key(item.label) {
                SpeedDialFABItem(
                    visible = expanded,
                    label = item.label,
                    icon = item.icon,
                    onClick = {
                        onExpandedChange(false)
                        item.onClick()
                    }
                )
                Spacer(Modifier.height(8.dp))
            }
        }

        val rotation by animateFloatAsState(
            targetValue = if (expanded) 45f else 0f,
            label = "fabRotation"
        )
        FloatingActionButton(onClick = { onExpandedChange(!expanded) }) {
            Icon(
                imageVector = AppIcons.Add,
                contentDescription = if (expanded) "Close menu" else "Open menu",
                modifier = Modifier.graphicsLayer { rotationZ = rotation }
            )
        }
    }
}

@Composable
fun KFabList(items: List<SpeedDialItem>, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    KFabList(items, modifier, expanded) { expanded = it }
}

