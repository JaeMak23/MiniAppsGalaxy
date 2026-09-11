package com.jaemak23.miniappsgalaxy.feature.appcatalog.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jaemak23.miniappsgalaxy.core.navigation.AppList
import com.jaemak23.miniappsgalaxy.core.ui.ObserveAsEvents
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.DeviceSize
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.LocalDeviceWidth
import com.jaemak23.miniappsgalaxy.feature.appcatalog.domain.model.AppCatalogItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppsScreenRoot(
    onAppNav: (AppList) -> Unit,
    viewModel: AppsViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is AppsEvent.NavigateToApp -> onAppNav(event.app)
        }
    }

    AppsScreen(state = state, onAction = viewModel::onAction)
}

@Composable
fun AppsScreen(
    state: AppsState,
    onAction: (AppsAction) -> Unit,
) {
    val div = LocalDeviceWidth.current
    val columnCount = when(div){
        DeviceSize.DESKTOP -> GridCells.Adaptive(minSize = 200.dp)
        DeviceSize.TABLET -> GridCells.Adaptive(minSize = 160.dp)
        DeviceSize.FOLDABLE -> GridCells.Fixed(3)
        DeviceSize.PHONE -> GridCells.Fixed(2)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Text("$div")
        LazyVerticalGrid(
            columns = columnCount,
            modifier = Modifier.weight(1f).fillMaxWidth().safeDrawingPadding(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(state.apps, key = { it.id }) { app ->
                AppCatalogCard(app = app, onClick = { onAction(AppsAction.OnAppClick(app.id)) })
            }
        }
    }
}

@Composable
fun AppCatalogCard(
    app: AppCatalogItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(160.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                imageVector = app.icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary,
            )
            Column {
                Text(app.title, style = MaterialTheme.typography.titleMedium, maxLines = 1)
                Text(
                    app.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text("Ver : V${app.version}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}