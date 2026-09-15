package com.jaemak23.miniappsgalaxy.core.data

import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.path

// Web has no OS filesystem path — PlatformFile is backed by a browser File/Blob handle.
// Using the file name as a stand-in; callers that re-read content by this "path"
// (e.g. FileAccessDataSource.readFile) will NOT work on web and need a separate
// in-memory-content flow for the web target.
actual fun PlatformFile.resolveFilePath(): String = this.path