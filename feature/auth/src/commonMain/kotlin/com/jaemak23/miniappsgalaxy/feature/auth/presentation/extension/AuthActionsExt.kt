package com.jaemak23.miniappsgalaxy.feature.auth.presentation.extension

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthActions

fun AuthActions.toAnnotatedString(
    actionColor: Color,
    onClick: (LinkAnnotation) -> Unit
): AnnotatedString? {
    val linkText = linkText ?: return null

    return buildAnnotatedString {
        text?.let { append(it) }

        val link = LinkAnnotation.Clickable(
            tag = "auth-actions-link",
            styles = TextLinkStyles(
                style = SpanStyle(
                    color = actionColor,
                    fontWeight = FontWeight.Bold
                )
            ),
            linkInteractionListener = onClick
        )
        withLink(link) {
            append(linkText)
        }
    }
}