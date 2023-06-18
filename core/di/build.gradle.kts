plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

apply {
    from("$rootDir/android-common.gradle")
}

dependencies {

    val external = Dependencies.External

    implementation(external.hilt)
    kapt(external.hiltCompiler)
    implementation (external.hiltCompose)
}

kapt {
    correctErrorTypes = true
}