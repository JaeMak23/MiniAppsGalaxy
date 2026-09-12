package com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.components.ExitButton
import com.jaemak23.miniappsgalaxy.core.ui.components.ThemeActionButton
import com.jaemak23.miniappsgalaxy.core.ui.components.TooltipIconButton
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons
import com.jaemak23.miniappsgalaxy.core.ui.theme.ComponentPreview

sealed interface TextAreaColors {
    val background: Color
    val body: Color
    val border: Color
}

data class Custom(
    override val background: Color = Color(0xFF161919),
    override val body: Color = Color(0xFF0B0C0E),
    override val border: Color = Color(0xFF2E3131)
) : TextAreaColors

sealed interface TextAreaColorDefaults : TextAreaColors {
    override val background: Color
    override val body: Color
    override val border: Color
}

object DarkModeDefaults : TextAreaColorDefaults {
    override val background: Color = Color(0xFF161919)
    override val body: Color = Color(0xFF0B0C0E)
    override val border: Color = Color(0xFF2E3131)
}

object LightModeDefaults : TextAreaColorDefaults {
    override val background: Color = Color(0xFFF1F5F4)
    override val body: Color = Color(0xFFFDFDFD)
    override val border: Color = Color(0xFFD9DDDC)
}

@Composable
fun KWindowContainer(
    modifier: Modifier = Modifier,
    colors: TextAreaColors = DarkModeDefaults,
    headerContent: @Composable (RowScope.() -> Unit)? = null,
    bodyContent: @Composable (BoxScope.() -> Unit)
) {
    Column(
        modifier = modifier.panelBorder()
            .background(colors.background)
            .padding(4.dp)
    ) {

        headerContent?.let {
            Row(
                Modifier.fillMaxWidth().heightIn(min = 32.dp).padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                content = headerContent
            )
        }

        Box(
            Modifier.weight(1f).fillMaxWidth()
                .panelBorder(borderWidth = 0.dp)
                .background(colors.body)
                .padding(4.dp),
            content = bodyContent
        )
    }
}

@Composable
private fun KWin(colors: TextAreaColors = DarkModeDefaults) {
    KWindowContainer(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        colors = colors,
        headerContent = {
            Text(
                "Header",
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onSurface
            )
            ThemeActionButton()
            TooltipIconButton("Simple", {}) {
                Icon(AppIcons.Save, "")
            }
            ExitButton { }
        }) {}
}

@Preview(showBackground = true)
@Composable
private fun TextPreviewLight() {
    ComponentPreview { KWin(LightModeDefaults) }
}

@Preview(showBackground = true)
@Composable
private fun TextPreview() {
    ComponentPreview(darkMode = true) { KWin() }
}