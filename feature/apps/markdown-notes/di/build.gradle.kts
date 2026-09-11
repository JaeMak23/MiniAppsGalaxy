plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
}

kotlin {
    jvm()

    android {
        namespace = "com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.di"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.data)
            implementation(projects.core.domain)
            implementation(projects.feature.apps.markdownNotes.data)
            implementation(projects.feature.apps.markdownNotes.domain)
            implementation(projects.feature.apps.markdownNotes.presentation)

            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)

            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.koin.compose.viewmodel)
        }
    }
}