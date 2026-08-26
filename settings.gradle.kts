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

rootProject.name = "AppsDAO"

// App 1 - Cadastro de Alunos (nome + matrícula aleatória)
include(":app1_cadastro_alunos")

// App 2 - Calculadora de IMC (peso + altura -> IMC e classificação)
include(":app2_calculo_imc")
