package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

@Composable
fun ViewModeToolbar(
    viewMode: EditorViewMode,
    onSetViewMode: (EditorViewMode) -> Unit,
    modifier: Modifier = Modifier
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier.height(40.dp)
    ) {
        SegmentedButton(
            selected = viewMode is EditorViewMode.EditorOnly,
            onClick = { onSetViewMode(EditorViewMode.EditorOnly) },
            shape = SegmentedButtonDefaults.itemShape(index = 0, count = 3),
            icon = {}
        ) {
            Icon(
                AppIcons.Edit,
                contentDescription = "Editor only",
                modifier = Modifier.height(16.dp)
            )
        }
        SegmentedButton(
            selected = viewMode is EditorViewMode.Split,
            onClick = { onSetViewMode(EditorViewMode.Split(ratio = 0.5f)) },
            shape = SegmentedButtonDefaults.itemShape(index = 1, count = 3),
            icon = {}
        ) {
            Icon(
                AppIcons.SplitIconHorizontal,
                contentDescription = "Split",
                modifier = Modifier.height(16.dp)
            )
        }
        SegmentedButton(
            selected = viewMode is EditorViewMode.PreviewOnly,
            onClick = { onSetViewMode(EditorViewMode.PreviewOnly) },
            shape = SegmentedButtonDefaults.itemShape(index = 2, count = 3),
            icon = {}
        ) {
            Icon(
                AppIcons.Visibility,
                contentDescription = "Preview only",
                modifier = Modifier.height(16.dp)
            )
        }
    }
}