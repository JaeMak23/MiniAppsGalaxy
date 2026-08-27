package com.jaemak23.miniappsgalaxy.feature.auth.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons

sealed interface AuthActions {
    val buttonText: String
    val buttonIcon: ImageVector?
    val text: String?
    val linkText: String?


    data object Login : AuthActions {
        override val buttonText: String = "Login"
        override val buttonIcon: ImageVector = AppIcons.Account
        override val text: String = "Don't have an account? "
        override val linkText: String = "Sign Up"
    }

    data object Signup : AuthActions {
        override val buttonText: String = "Signup"
        override val buttonIcon: ImageVector = AppIcons.Account
        override val text: String = "Already have an account? "
        override val linkText: String = "Login"
    }

    data object ForgotPassword : AuthActions {
        override val buttonText: String = "Send OTP"
        override val buttonIcon: ImageVector? = null
        override val text: String = "Remembered password? "
        override val linkText: String = "Login"
    }

    data object ResetPassword : AuthActions {
        override val buttonText: String = "Reset Password"
        override val buttonIcon: ImageVector? = null
        override val text: String? = null
        override val linkText: String? = null
        val resendText: String = "Resend OTP"
    }
}