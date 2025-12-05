pluginManagement {
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

rootProject.name = "Evently"
include(":app")
include(":core:data")
include(":core:model")
include(":core:network")
include(":core:database")
include(":core:datastore")
include(":core:designsystem")
include(":feature:onboarding")
include(":feature:auth")
include(":feature:home")
include(":feature:map")
include(":feature:favorites")
include(":feature:profile")
include(":feature:event")
