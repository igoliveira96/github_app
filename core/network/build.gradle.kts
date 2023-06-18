plugins {
    id("kotlin")
}

dependencies {

    val external = Dependencies.External
    val test = Dependencies.Test

    implementation(external.okHttp)
    implementation(external.retrofit)
    implementation(external.retrofitConverterGson)
    implementation(external.okHttpLoggingInterceptor)
    implementation(external.javaxInject)

    testImplementation(test.jUnit)
}