package com.jaemak23.miniappsgalaxy.core.navigation.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthRoute : NavKey {
    @Serializable
    data object Login : AuthRoute

    @Serializable
    data object Signup : AuthRoute

    @Serializable
    data object ForgotPassword : AuthRoute
}