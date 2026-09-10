package com.jaemak23.miniappsgalaxy.core.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

@Composable fun  ExitButton(title:String="Exit",onExit: () -> Unit={}) {
    OutlinedButton(onExit) {
        Icon(AppIcons.Exit, title)
        Text(title)
    }
}