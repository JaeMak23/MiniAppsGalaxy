enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "MiniAppsGalaxy"

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":androidApp")
include(":desktopApp")
include(":shared")
// core
include(
    ":core:common",
    ":core:data",
    ":core:di",
    ":core:domain",
    ":core:navigation",
    ":core:network",
    ":core:ui",
    ":core:util",
)
// feature
include(
    ":feature:auth",
    ":feature:splash",
    ":feature:dashboard",
    ":feature:app-catalog",
    ":feature:game-catalog"
)
// apps feature
include("",
    ":feature:apps:markdown-notes:data",
    ":feature:apps:markdown-notes:di",
    ":feature:apps:markdown-notes:domain",
    ":feature:apps:markdown-notes:presentation",
    ":feature:apps:html-editor"
)
// feature : games
include(":feature:games:tic-tac-toe")