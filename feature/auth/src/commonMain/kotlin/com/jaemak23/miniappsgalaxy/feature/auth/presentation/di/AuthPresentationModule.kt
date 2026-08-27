package com.jaemak23.miniappsgalaxy.feature.auth.presentation.di

import com.jaemak23.miniappsgalaxy.feature.auth.presentation.screens.forgotpassword.ForgotPasswordViewModel
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.screens.login.LoginViewModel
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.screens.signup.SignupViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val authPresentationModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::SignupViewModel)
    viewModelOf(::ForgotPasswordViewModel)
}