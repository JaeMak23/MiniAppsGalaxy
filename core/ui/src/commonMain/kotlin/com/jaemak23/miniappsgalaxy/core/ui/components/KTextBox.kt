package com.jaemak23.miniappsgalaxy.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons
import com.jaemak23.miniappsgalaxy.core.ui.theme.ComponentPreview

private val KTextFieldShape = RoundedCornerShape(12.dp)

@Composable
private fun kTextFieldColors() = OutlinedTextFieldDefaults.colors(
    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
    focusedContainerColor = MaterialTheme.colorScheme.surface,
    errorContainerColor = MaterialTheme.colorScheme.surface,
    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
    focusedBorderColor = MaterialTheme.colorScheme.primary,
)

@Composable
private fun KFieldLabel(label: String, isError: Boolean) {
    if (label.isNotEmpty()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
        )
    }
}

@Composable
private fun KFieldError(errorMessage: String?) {
    errorMessage?.let {
        Text(
            text = it,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 12.dp, top = 4.dp)
        )
    }
}

@Composable
fun KTextBox(
    modifier: Modifier = Modifier,
    state: TextFieldState = rememberTextFieldState(),
    label: String = "",
    icon: ImageVector? = null,
    placeholder: String? = null,
    errorMessage: String? = null,
    maxWidth: Dp = 600.dp
) {
    val isError = errorMessage != null

    Column(
        modifier = modifier
            .widthIn(max = maxWidth)
            .fillMaxWidth()
    ) {
        KFieldLabel(label, isError)

        OutlinedTextField(
            state = state,
            modifier = Modifier.fillMaxWidth(),
            shape = KTextFieldShape,
            colors = kTextFieldColors(),
            isError = isError,
            leadingIcon = icon?.let { { Icon(it, contentDescription = null) } },
            placeholder = placeholder?.let { { Text(it, style = MaterialTheme.typography.bodyLarge) } },
            lineLimits = TextFieldLineLimits.SingleLine
        )

        KFieldError(errorMessage)
    }
}

@Composable
fun KPasswordTextBox(
    modifier: Modifier = Modifier,
    state: TextFieldState = rememberTextFieldState(),
    label: String = "",
    icon: ImageVector? = null,
    placeholder: String? = null,
    errorMessage: String? = null,
    maxWidth: Dp = 600.dp
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val isError = errorMessage != null

    Column(
        modifier = modifier
            .widthIn(max = maxWidth)
            .fillMaxWidth()
    ) {
        KFieldLabel(label, isError)

        OutlinedSecureTextField(
            state = state,
            modifier = Modifier.fillMaxWidth(),
            shape = KTextFieldShape,
            colors = kTextFieldColors(),
            isError = isError,
            leadingIcon = icon?.let { { Icon(it, contentDescription = null) } },
            placeholder = placeholder?.let { { Text(it, style = MaterialTheme.typography.bodyLarge) } },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) AppIcons.Visibility else AppIcons.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            textObfuscationMode = if (passwordVisible) TextObfuscationMode.Visible else TextObfuscationMode.Hidden,
        )

        KFieldError(errorMessage)
    }
}

@Composable
@Preview(showBackground = true)
private fun KTextBoxPreview() {
    ComponentPreview {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            KTextBox(
                label = "Email",
                placeholder = "Enter your email",
                icon = AppIcons.Email
            )

            KTextBox(
                label = "Username",
                placeholder = "Enter username",
                errorMessage = "Username is already taken"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun KPasswordTextBoxPreview() {
    ComponentPreview {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            KPasswordTextBox(
                label = "Password",
                placeholder = "Enter password"
            )

            KPasswordTextBox(
                label = "Password",
                placeholder = "Enter password",
                errorMessage = "Password must be at least 8 characters"
            )
        }
    }
}