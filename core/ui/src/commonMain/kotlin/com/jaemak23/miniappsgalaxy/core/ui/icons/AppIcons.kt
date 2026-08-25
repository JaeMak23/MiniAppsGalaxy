package com.jaemak23.miniappsgalaxy.core.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * A central object to manage App Icons.
 * Uses Icons.Default (Material core) where possible to keep app size small;
 * custom ImageVectors are used only where Material core doesn't provide one.
 */
object AppIcons {

    val Account: ImageVector = Icons.Default.AccountCircle
    val Email : ImageVector = Icons.Default.Email

    val ArrowBack: ImageVector = Icons.AutoMirrored.Default.ArrowBack
    val ArrowNext: ImageVector = Icons.AutoMirrored.Default.ArrowForward


    val Logo: ImageVector = Icons.Default.ShoppingCart
    val Lock: ImageVector = Icons.Default.Lock

    object Home {
        val Outlined: ImageVector = Icons.Outlined.Home
        val Filled: ImageVector = Icons.Filled.Home
    }

    object Person {
        val Outlined: ImageVector = Icons.Outlined.Person
        val Filled: ImageVector = Icons.Filled.Person
    }

    object Settings {
        val Outlined: ImageVector = Icons.Outlined.Settings
        val Filled: ImageVector = Icons.Filled.Settings
    }

    // Custom ImageVectors (not available in Material core icons)
    val LightMode: ImageVector = CustomLightMode
    val DarkMode: ImageVector = CustomDarkMode
    val Visibility: ImageVector = CustomVisibility
    val VisibilityOff: ImageVector = CustomVisibilityOff
}
