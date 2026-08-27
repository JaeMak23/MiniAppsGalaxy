package com.jaemak23.miniappsgalaxy.feature.auth.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

sealed class AuthTextInputType(
    val text: String,
    val icon: ImageVector,
    val isPassword: Boolean = false,
    val placeholder: String = text,
    val maxWidth: Dp = 600.dp,
) {
    data object UserName : AuthTextInputType("User Name", AppIcons.Account)
    data object Password : AuthTextInputType("Password", AppIcons.Lock, isPassword = true)
    data object RetypePassword :
        AuthTextInputType("Re-type Password", AppIcons.Lock, isPassword = true)
}