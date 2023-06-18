plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
}

apply {
    from("$rootDir/android-common.gradle")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    }
}

dependencies {

    val core = Dependencies.Modules.Core
    val data = Dependencies.Modules.Data
    val compose = Dependencies.Compose
    val external = Dependencies.External
    val features = Dependencies.Modules.Features

    implementation(project(core.uikit))
    implementation(project(core.di))

    implementation(project(features.home))
    implementation(project(features.repositories))

    implementation(project(data.repo))

    implementation(compose.navigation)
    implementation(compose.navigationCommon)

    implementation(external.gson)
    implementation(external.hilt)
    kapt(external.hiltCompiler)
    implementation(external.hiltCompose)

    testImplementation(Dependencies.Test.jUnit)

}

kapt {
    correctErrorTypes = true
}