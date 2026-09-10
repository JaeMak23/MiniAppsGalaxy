package com.jaemak23.miniappsgalaxy.core.data

import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.path

actual fun PlatformFile.resolveFilePath() : String = this.path