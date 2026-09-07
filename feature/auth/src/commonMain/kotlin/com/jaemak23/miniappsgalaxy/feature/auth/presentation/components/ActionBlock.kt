package com.jaemak23.miniappsgalaxy.feature.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.components.KButton
import com.jaemak23.miniappsgalaxy.core.ui.extensions.maxReadableWidth
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.extension.toAnnotatedString
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthActions

@Composable
fun ActionBlock(
    action: AuthActions,
    modifier: Modifier = Modifier,
    onLinkClick: () -> Unit,
    onButtonClick: () -> Unit,
) {
    val annotatedString =
        action.toAnnotatedString(MaterialTheme.colorScheme.primary) { onLinkClick() }

    Column(
        modifier = modifier.maxReadableWidth(320.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        KButton(
            modifier = Modifier.fillMaxWidth(),
            text = action.buttonText,
            icon = action.buttonIcon,
            contentDescription = "${action.buttonText} Button",
            onClick = onButtonClick
        )

        annotatedString?.let { str ->
            Text(
                text = str,
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    }
}