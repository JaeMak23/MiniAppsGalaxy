package com.jaemak23.miniappsgalaxy.core.ui.error

import com.jaemak23.miniappsgalaxy.core.domain.DataError

data class UiText(val value: String)


fun DataError.toUiText(): UiText {
    // TODO: replace hardcoded strings with proper copy / localization when core:presentation lands
    return when (this) {
        // Local
        DataError.Local.DISK_FULL -> UiText("Disk Full")
        DataError.Local.NOT_FOUND -> UiText("Not Found")
        DataError.Local.UNKNOWN -> UiText("Unknown")
        // Network
        DataError.Network.BAD_REQUEST -> UiText("Bad Request")
        DataError.Network.REQUEST_TIMEOUT -> UiText("Request Timeout")
        DataError.Network.UNAUTHORIZED -> UiText("Unauthorized")
        DataError.Network.FORBIDDEN -> UiText("Forbidden")
        DataError.Network.NOT_FOUND -> UiText("Not Found")
        DataError.Network.CONFLICT -> UiText("Conflict")
        DataError.Network.TOO_MANY_REQUESTS -> UiText("Too Many Requests")
        DataError.Network.NO_INTERNET -> UiText("No Internet")
        DataError.Network.PAYLOAD_TOO_LARGE -> UiText("Payload Too Large")
        DataError.Network.SERVER_ERROR -> UiText("Server Error")
        DataError.Network.SERVICE_UNAVAILABLE -> UiText("Service Unavailable")
        DataError.Network.SERIALIZATION -> UiText("Serialization")
        DataError.Network.UNKNOWN -> UiText("Unknown")
    }
}