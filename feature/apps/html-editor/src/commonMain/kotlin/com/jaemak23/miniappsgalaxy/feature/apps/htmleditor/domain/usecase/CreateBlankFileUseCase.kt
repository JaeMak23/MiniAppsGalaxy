package com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.domain.usecase

import com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.domain.model.HtmlFile

class CreateBlankFileUseCase {
    suspend operator fun invoke(): HtmlFile {
        return HtmlFile(
            title = "Untitled",
            content = DEFAULT_HTML_TEMPLATE,
            filePath = null
        )
    }

    companion object {
        private const val DEFAULT_HTML_TEMPLATE = """<!DOCTYPE html>
<html>
<head>
    <title>Untitled</title>
</head>
<body>

</body>
</html>"""
    }
}