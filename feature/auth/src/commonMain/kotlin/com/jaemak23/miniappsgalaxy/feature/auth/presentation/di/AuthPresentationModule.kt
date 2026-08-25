package com.jaemak23.miniappsgalaxy.feature.auth.presentation.di

import com.jaemak23.miniappsgalaxy.feature.auth.presentation.forgotpassword.ForgotPasswordViewModel
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.login.LoginViewModel
import com.jaemak23.miniappsgalaxy.feature.auth.presentation.signup.SignupViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authPresentationModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::SignupViewModel)
    viewModelOf(::ForgotPasswordViewModel)
}