plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

apply {
    from("$rootDir/android-common.gradle")
}

android {
    buildFeatures {
        compose = true
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    }
}

dependencies {

    val core = Dependencies.Modules.Core
    val data = Dependencies.Modules.Data
    val android = Dependencies.Android
    val compose = Dependencies.Compose
    val external = Dependencies.External
    val test = Dependencies.Test

    implementation(compose.ui)
    implementation(compose.material)
    implementation(compose.uiToolingPreview)
    implementation(compose.coil)
    implementation(compose.accompanistPager)
    implementation(compose.accompanistPagerIndicators)
    implementation(compose.paging)

    implementation(android.coreKtx)
    implementation(android.lifecycleViewModel)
    implementation(android.lifecycleCompose)
    coreLibraryDesugaring(android.desbugar)

    implementation(external.hilt)
    kapt(external.hiltCompiler)
    implementation (external.hiltCompose)

    implementation(project(core.commons))
    implementation(project(core.uikit))
    implementation(project(core.features))

    implementation(project(data.repo))

    testImplementation(test.jUnit)
    debugImplementation(compose.uiTooling)

    androidTestImplementation(test.composeJUnit)
    androidTestImplementation(test.androidxTestRules)
    debugImplementation(test.composeManifest)
}

kapt {
    correctErrorTypes = true
}