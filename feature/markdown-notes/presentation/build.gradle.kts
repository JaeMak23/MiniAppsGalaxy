plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    jvm()

    android {
        namespace = "com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.compose.uiTooling)
        }
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.data)
            implementation(projects.core.di)
            implementation(projects.core.domain)
            implementation(projects.core.navigation)
            implementation(projects.core.network)
            implementation(projects.core.ui)
            implementation(projects.core.util)
            implementation(projects.feature.markdownNotes.domain)

            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.jetbrains.material3.adaptiveNavigation3)
            implementation(libs.compose.uiToolingPreview)

            implementation(libs.kotlinx.datetime)

            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.koin.compose.viewmodel)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}