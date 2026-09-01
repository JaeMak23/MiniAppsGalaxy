package com.jaemak23.miniappsgalaxy.core.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * A central object to manage App Icons.
 */
object AppIcons {

    val Account: ImageVector = Icons.Default.AccountCircle
    val Email: ImageVector = Icons.Default.Email
    val Menu: ImageVector = Icons.Default.Menu

    val ArrowBack: ImageVector = Icons.AutoMirrored.Default.ArrowBack
    val ArrowNext: ImageVector = Icons.AutoMirrored.Default.ArrowForward
    val ArrowDropDown: ImageVector = Icons.Default.ArrowDropDown

    val Logo: ImageVector = Icons.Default.ShoppingCart
    val Lock: ImageVector = Icons.Default.Lock
    val Add : ImageVector = Icons.Default.Add
    val Delete : ImageVector = Icons.Default.Delete

    object Home {
        val Outlined: ImageVector = Icons.Outlined.Home
        val Filled: ImageVector = Icons.Filled.Home
    }

    object Apps {
        val Outlined: ImageVector = AppsOutlineIcon
        val Filled: ImageVector = AppsFilledIcon
    }

    object Games {
        val Outlined: ImageVector = GamesOutlineIcon
        val Filled: ImageVector = GamesFilledIcon
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
    val MenuOpen: ImageVector = MenuOpenIcon
    val Visibility: ImageVector = CustomVisibility
    val VisibilityOff: ImageVector = CustomVisibilityOff
    val FileUpload : ImageVector = ComputerArrowUp
    val FileOpen : ImageVector = FileOpenIcon
}
