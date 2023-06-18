plugins {
    id("kotlin")
}

dependencies {

    val external = Dependencies.External
    val test = Dependencies.Test

    implementation(external.coroutinesCore)
    implementation(external.coroutinesCore)
    implementation(external.javaxInject)
    implementation(external.hiltCore)

    testImplementation(test.jUnit)

}