package com.jaemak23.miniappsgalaxy.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit.panelBorder

@Composable
fun FileNameTitleTextField(
    title: String,
    placeHolderText: String,
    onTitleChanged: (String) -> Unit,
) {
    Box(
        modifier = Modifier.panelBorder()
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        if (title.isBlank()) {
            Text(
                placeHolderText,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
        BasicTextField(
            value = title,
            onValueChange = onTitleChanged,
            singleLine = true,
            textStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}