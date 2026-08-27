package com.jaemak23.miniappsgalaxy.feature.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jaemak23.miniappsgalaxy.core.ui.theme.ComponentPreview
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthCaptions

@Composable
fun AuthHeader(
    text: String,
    subText: String,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = subText,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AuthCaptionContainerPreview() {
    ComponentPreview {
        Column {
            val header1 = AuthCaptions.Login
            AuthHeader(header1.heading, header1.description)

            val header2 = AuthCaptions.Signup
            AuthHeader(header2.heading, header2.description)

            val header3 = AuthCaptions.ForgotPassword
            AuthHeader(header3.heading, header3.description)

            val header4 = AuthCaptions.ResetPassword
            AuthHeader(header4.heading, header4.description)
        }
    }
}

