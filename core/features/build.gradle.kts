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
    val compose = Dependencies.Compose
    val external = Dependencies.External

    implementation(project(core.uikit))
    implementation(project(core.di))

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