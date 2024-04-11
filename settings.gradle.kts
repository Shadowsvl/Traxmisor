pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Traxmisor"
include(":app")
include(":core:common")
include(":core:design-system")
include(":core:ui")
include(":core:model")
include(":core:data")
include(":core:network")
include(":core:data-store")
include(":feature:home")
include(":feature:login")
