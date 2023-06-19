object Dependencies {

    object Modules {

        object Core {
            const val commons = ":core:commons"
            const val uikit = ":core:uikit"
            const val intent = ":core:intent"
            const val di = ":core:di"
            const val network = ":core:network"
        }

        object Features {
            const val home = ":features:home"
            const val repositories = ":features:repo"
        }

        object Data {
            const val repo = ":data:repo"
        }

    }

    object Android {
        const val coreKtx = "androidx.core:core-ktx:${Versions.Android.coreKtx}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.lifecycle}"
        const val lifecycleCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.Android.lifecycle}"
        const val desbugar = "com.android.tools:desugar_jdk_libs:${Versions.Android.desbugar}"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.Compose.core}"
        const val material = "androidx.compose.material:material:${Versions.Compose.core}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.Compose.core}"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.Compose.core}"
        const val uiUtil = "androidx.compose.ui:ui-util:${Versions.Compose.core}"
        const val activity = "androidx.activity:activity-compose:${Versions.Compose.activity}"
        const val coil = "io.coil-kt:coil-compose:${Versions.Compose.coil}"
        const val accompanistSystemUi = "com.google.accompanist:accompanist-systemuicontroller:${Versions.Compose.accompanist}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.Compose.navigation}"
        const val navigationCommon = "androidx.navigation:navigation-common-ktx:${Versions.Compose.navigation}"
        const val accompanistPager = "com.google.accompanist:accompanist-pager:${Versions.Compose.accompanist}"
        const val accompanistPagerIndicators = "com.google.accompanist:accompanist-pager-indicators:${Versions.Compose.accompanist}"
        const val paging = "androidx.paging:paging-compose:${Versions.Compose.paging}"
    }

    object External {
        const val gson = "com.google.code.gson:gson:${Versions.External.gson}"
        const val hilt = "com.google.dagger:hilt-android:${Versions.External.hilt}"
        const val hiltCore = "com.google.dagger:hilt-core:${Versions.External.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.External.hilt}"
        const val hiltCompose = "androidx.hilt:hilt-navigation-compose:${Versions.External.hiltCompose}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.External.okHttp}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.External.retrofit}"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.External.retrofit}"
        const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.External.okHttp}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.External.coroutines}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.External.coroutines}"
        const val javaxInject = "javax.inject:javax.inject:${Versions.External.javaxInject}"
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.Test.jUnit}"
        const val mockito = "org.mockito:mockito-core:${Versions.Test.mockito}"
        const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.Test.mockitoKotlin}"
        const val androidxTestRules = "androidx.test:rules:${Versions.Test.androidTestRules}"
        const val composeJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.Compose.core}"
        const val composeManifest = "androidx.compose.ui:ui-test-manifest:${Versions.Compose.core}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Test.coroutinesTest}"
    }

}