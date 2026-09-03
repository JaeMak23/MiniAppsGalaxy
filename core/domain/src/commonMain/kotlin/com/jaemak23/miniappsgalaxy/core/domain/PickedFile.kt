package com.jaemak23.miniappsgalaxy.core.domain

data class PickedFile(
    val fileName: String,
    val content: String,
    val filePath: String?
)

interface FileAccessDataSource {
    suspend fun pickAndReadFile(extensions: List<String>): Result<PickedFile?, DataError.Local>
}