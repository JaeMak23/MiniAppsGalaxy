package com.jaemak23.miniappsgalaxy.feature.apps.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaemak23.miniappsgalaxy.feature.apps.data.AppCatalog
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppsViewModel : ViewModel() {
    private val _state = MutableStateFlow(AppsState(apps = AppCatalog.getApps()))
    val state = _state.asStateFlow()

    private val _events = Channel<AppsEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: AppsAction) {
        when (action) {
            is AppsAction.OnAppClick -> {
                viewModelScope.launch {
                    _events.send(AppsEvent.NavigateToApp(action.app))
                }
            }
        }
    }
}