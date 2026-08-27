package com.jaemak23.miniappsgalaxy.feature.auth.presentation.components.authbody.authinput

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.extension.ToKPasswordTextBox
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.extension.ToKTextBox
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.model.AuthTextInputType

sealed interface AuthInputBundle {
    @Composable
    fun ColumnScope.Fields()

    data class Login(
        val loginState: TextFieldState,
        val passwordState: TextFieldState
    ) : AuthInputBundle {
        @Composable
        override fun ColumnScope.Fields() {
            AuthTextInputType.UserName.ToKTextBox(loginState)
            AuthTextInputType.Password.ToKPasswordTextBox(passwordState)
        }
    }

    data class Signup(
        val loginState: TextFieldState,
        val passwordState: TextFieldState,
        val retypePassword: TextFieldState
    ) : AuthInputBundle {
        @Composable
        override fun ColumnScope.Fields() {
            AuthTextInputType.UserName.ToKTextBox(loginState)
            AuthTextInputType.Password.ToKPasswordTextBox(passwordState)
            AuthTextInputType.RetypePassword.ToKPasswordTextBox(retypePassword)
        }
    }
}