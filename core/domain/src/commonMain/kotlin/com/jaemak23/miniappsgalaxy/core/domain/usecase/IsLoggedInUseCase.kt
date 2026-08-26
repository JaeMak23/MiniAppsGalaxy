package com.jaemak23.miniappsgalaxy.core.domain.usecase

import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class IsLoggedInUseCase {
    suspend operator fun invoke(): Boolean {
        delay(1000.milliseconds) // simulate session/token check
        return true
    }
}