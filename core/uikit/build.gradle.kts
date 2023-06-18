plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
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

    val android = Dependencies.Android
    val compose = Dependencies.Compose

    implementation(compose.ui)
    implementation(compose.uiUtil)
    implementation(compose.material)
    implementation(compose.uiToolingPreview)
    implementation(compose.activity)
    implementation(compose.coil)
    implementation(compose.accompanistSystemUi)
    coreLibraryDesugaring(android.desbugar)

    testImplementation(Dependencies.Test.jUnit)
    debugImplementation(compose.uiTooling)

}