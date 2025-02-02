pluginManagement {
    repositories {
        maven {
            setUrl( "https://maven.myket.ir")
        }
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven { setUrl("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            setUrl("https://maven.myket.ir")
        }
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io")}
    }
}

rootProject.name = "Movies App"
include(":app")
 