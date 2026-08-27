package com.jaemak23.miniappsgalaxy.feature.auth.presentation.extension

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jaemak23.miniappsgalaxy.core.ui.components.KPasswordTextBox
import com.jaemak23.miniappsgalaxy.core.ui.components.KTextBox
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthTextInputType

@Composable
fun AuthTextInputType.ToKTextBox(
    state: TextFieldState,
    modifier: Modifier = Modifier
) {
    if (!isPassword)
        KTextBox(
            modifier = modifier,
            state = state,
            label = text,
            icon = icon,
            placeholder = placeholder,
            maxWidth = maxWidth
        )
}

@Composable
fun AuthTextInputType.ToKPasswordTextBox(
    state: TextFieldState,
    modifier: Modifier = Modifier
) {
    if (isPassword)
        KPasswordTextBox(
            modifier = modifier,
            state = state,
            label = text,
            icon = icon,
            placeholder = placeholder,
            maxWidth = maxWidth
        )
}