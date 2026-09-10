package com.jaemak23.miniappsgalaxy.core.data

import io.github.vinceglb.filekit.PlatformFile

actual fun PlatformFile.resolveFilePath(): String = this.file.absolutePath