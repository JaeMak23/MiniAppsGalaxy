package com.jaemak23.miniappsgalaxy.core.data

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.FileAccessDataSource
import com.jaemak23.miniappsgalaxy.core.domain.Result
import com.jaemak23.miniappsgalaxy.core.domain.model.PickedFile
import com.jaemak23.miniappsgalaxy.core.domain.model.PickedFilePath
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.dialogs.FileKitType
import io.github.vinceglb.filekit.dialogs.openFilePicker
import io.github.vinceglb.filekit.dialogs.openFileSaver
import io.github.vinceglb.filekit.name
import io.github.vinceglb.filekit.readString
import io.github.vinceglb.filekit.writeString

class FileKitFileAccessDataSource : FileAccessDataSource {

    override suspend fun pickAndReadFile(extensions: List<String>): Result<PickedFile?, DataError.Local> {
        return try {
            val file = FileKit.openFilePicker(
                type = FileKitType.File(extensions = extensions)
            ) ?: return Result.Success(null)

            Result.Success(
                PickedFile(
                    fileName = file.name.substringBeforeLast("."),
                    content = file.readString(),
                    filePath = file.resolveFilePath()
                )
            )
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun pickFilePath(extensions: List<String>): Result<PickedFilePath?, DataError.Local> {
        return try {
            val file = FileKit.openFilePicker(
                type = FileKitType.File(extensions = extensions)
            ) ?: return Result.Success(null)

            Result.Success(
                PickedFilePath(
                    fileName = file.name.substringBeforeLast("."),
                    filePath = file.resolveFilePath()
                )
            )
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun readFile(filePath: String): Result<String, DataError.Local> {
        return try {
            val file = PlatformFile(filePath)
            Result.Success(file.readString())
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun saveFile(
        filePath: String?,
        suggestedName: String,
        defaultExtension: String,
        content: String
    ): Result<String?, DataError.Local> {
        return try {
            if (filePath != null) {
                val file = PlatformFile(filePath)
                file.writeString(content)
                Result.Success(filePath)
            } else {
                val file = FileKit.openFileSaver(
                    suggestedName = suggestedName,
                    defaultExtension = defaultExtension
                ) ?: return Result.Success(null)

                file.writeString(content)
                Result.Success(file.resolveFilePath())
            }
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun saveAsFile(
        suggestedName: String,
        defaultExtension: String,
        content: String
    ): Result<String?, DataError.Local> {
        return try {
            val file = FileKit.openFileSaver(
                suggestedName = suggestedName,
                defaultExtension = defaultExtension
            ) ?: return Result.Success(null)

            file.writeString(content)
            Result.Success(file.resolveFilePath())
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }
}