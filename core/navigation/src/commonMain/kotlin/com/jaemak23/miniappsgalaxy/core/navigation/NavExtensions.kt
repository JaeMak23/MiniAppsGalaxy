package com.jaemak23.miniappsgalaxy.core.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

fun NavBackStack<NavKey>.goBack() {
    if (this.size > 1) {
        this.removeAt(this.size - 1)
    }
}

/**
 * Extension function to handle tab switching logic.
 * Replaces the current stack with the new tab in an atomic-like fashion to avoid empty states.
 */
fun <T : NavKey> MutableList<T>.switchTab(tab: T) {
    if (lastOrNull() != tab) {
        if (isNotEmpty()) {
            // Atomic-ish update to avoid empty backstack during recomposition
            this[0] = tab
            while (size > 1) {
                removeAt(1)
            }
        } else {
            add(tab)
        }
    }
}

/**
 * Extension function to replace the entire backstack with a single route.
 */
fun <T : NavKey> MutableList<T>.replaceRoute(route: T) {
    if (isNotEmpty()) {
        this[0] = route
        while (size > 1) {
            removeAt(1)
        }
    } else {
        add(route)
    }
}
