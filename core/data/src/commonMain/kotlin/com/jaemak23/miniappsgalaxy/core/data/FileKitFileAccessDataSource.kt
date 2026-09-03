package com.jaemak23.miniappsgalaxy.core.data

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.FileAccessDataSource
import com.jaemak23.miniappsgalaxy.core.domain.PickedFile
import com.jaemak23.miniappsgalaxy.core.domain.Result
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.dialogs.FileKitType
import io.github.vinceglb.filekit.dialogs.openFilePicker
import io.github.vinceglb.filekit.name
import io.github.vinceglb.filekit.path
import io.github.vinceglb.filekit.readString

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
                    filePath = file.path
                )
            )
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }
}