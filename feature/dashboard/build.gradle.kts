plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    jvm()

    android {
        namespace = "com.jaemak23.miniappsgalaxy.feature.dashboard"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.compose.uiTooling)
        }
        commonMain.dependencies {
            implementation(project(":core:common"))
            implementation(project(":core:data"))
            implementation(project(":core:di"))
            implementation(project(":core:domain"))
            implementation(project(":core:navigation"))
            implementation(project(":core:network"))
            implementation(project(":core:ui"))
            implementation(project(":core:util"))

            implementation(projects.feature.markdownNotes.presentation)

            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.jetbrains.material3.adaptiveNavigation3)
            implementation(libs.compose.uiToolingPreview)

            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.koin.compose.viewmodel)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}