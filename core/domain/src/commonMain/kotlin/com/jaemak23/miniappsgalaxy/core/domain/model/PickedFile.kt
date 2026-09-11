package com.jaemak23.miniappsgalaxy.core.domain.model

data class PickedFile(
    val fileName: String,
    val content: String,
    val filePath: String?
)

data class PickedFilePath(
    val fileName: String,
    val filePath: String?
)