plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

apply {
    from("$rootDir/android-common.gradle")
}

android {
    defaultConfig.applicationId = AppConfig.applicationId

    productFlavors.all {
        when (this.name) {
            "dev" -> this.applicationId = ApplicationId.dev
            "prd" -> this.applicationId = ApplicationId.prd
        }
    }

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
    val android = Dependencies.Android
    val external = Dependencies.External
    val compose = Dependencies.Compose

    implementation(android.coreKtx)
    implementation(compose.ui)
    implementation(compose.material)
    implementation(compose.uiToolingPreview)
    implementation(android.lifecycleViewModel)
    implementation(compose.activity)
    implementation(compose.navigation)

    implementation(external.hilt)
    kapt(external.hiltCompiler)
    implementation (external.hiltCompose)

    coreLibraryDesugaring(Dependencies.Android.desbugar)

    implementation(project(core.uikit))
    implementation(project(core.intent))
    implementation(project(core.di))

    testImplementation(Dependencies.Test.jUnit)
    debugImplementation(compose.uiTooling)

}

kapt {
    correctErrorTypes = true
}