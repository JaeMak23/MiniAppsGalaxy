package com.jaemak23.miniappsgalaxy.feature.auth.presentation.model

sealed interface AuthCaptions {
    val heading: String
    val description: String

    data object Login : AuthCaptions {
        override val heading: String = "Welcome Back"
        override val description: String = "Please enter your details to sign in"
    }

    data object Signup : AuthCaptions {
        override val heading: String = "Create Account"
        override val description: String = "Join us to start your journey"
    }

    data object ForgotPassword : AuthCaptions {
        override val heading: String = "Forgot Password?"
        override val description: String = "Please enter your email address to reset your password"
    }

    data object ResetPassword : AuthCaptions {
        override val heading: String = "Reset Password"
        override val description: String = ""
    }
}

