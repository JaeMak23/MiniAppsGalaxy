package com.jaemak23.miniappsgalaxy.feature.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.components.KButton
import com.jaemak23.miniappsgalaxy.core.ui.extensions.maxReadableWidth
import com.jaemak23.miniappsgalaxy.core.ui.theme.ComponentPreview
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.extension.toAnnotatedString
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthActions

@Composable
fun AuthActionBlock(
    action: AuthActions,
    modifier: Modifier = Modifier,
    onLinkClick: () -> Unit,
    onButtonClick: () -> Unit,
) {
    val annotatedString =
        action.toAnnotatedString(MaterialTheme.colorScheme.primary) { onLinkClick() }

    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        KButton(
            modifier = Modifier.maxReadableWidth(),
            text = action.buttonText,
            icon = action.buttonIcon,
            contentDescription = "${action.buttonText} Button",
            onClick = onButtonClick
        )

        annotatedString?.let { str ->
            Text(
                text = str,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthActionBlockPreview() {
    ComponentPreview {
        Column {
            AuthActionBlock(AuthActions.Login, onButtonClick = {}, onLinkClick = {})
            AuthActionBlock(AuthActions.Signup, onButtonClick = {}, onLinkClick = {})
            AuthActionBlock(AuthActions.ForgotPassword, onButtonClick = {}, onLinkClick = {})
            AuthActionBlock(AuthActions.ResetPassword, onButtonClick = {}, onLinkClick = {})

        }
    }
}