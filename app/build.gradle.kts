import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}


android {
    namespace = "com.example.subreddit_app"
    compileSdk = 34
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        applicationId = "com.example.subreddit_app"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val localProperties = Properties().apply {
            load(File(rootDir, "local.properties").inputStream())
        }
        debug {
            buildConfigField("String", "REDDIT_USERNAME", "\"${localProperties["REDDIT_USERNAME"]}\"")
            buildConfigField("String", "REDDIT_PASSWORD", "\"${localProperties["REDDIT_PASSWORD"]}\"")
            buildConfigField("String", "REDDIT_CLIENT_ID", "\"${localProperties["REDDIT_CLIENT_ID"]}\"")
            buildConfigField("String", "REDDIT_CLIENT_SECRET", "\"${localProperties["REDDIT_CLIENT_SECRET"]}\"")
        }
        release {
            buildConfigField("String", "REDDIT_USERNAME", "\"${localProperties["REDDIT_USERNAME"]}\"")
            buildConfigField("String", "REDDIT_PASSWORD", "\"${localProperties["REDDIT_PASSWORD"]}\"")
            buildConfigField("String", "REDDIT_CLIENT_ID", "\"${localProperties["REDDIT_CLIENT_ID"]}\"")
            buildConfigField("String", "REDDIT_CLIENT_SECRET", "\"${localProperties["REDDIT_CLIENT_SECRET"]}\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.test.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.navigation.compose)

    // Views/Fragments integration
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // Feature module support for Fragments
    implementation(libs.androidx.navigation.dynamic.features.fragment)

    // Testing Navigation
    androidTestImplementation(libs.androidx.navigation.testing)

    // JSON serialization library
    implementation(libs.kotlinx.serialization.json)

    // Coil library
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Timber library
    implementation(libs.timber)

    implementation(libs.hilt.android)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation (libs.google.gson)
    kapt(libs.hilt.compiler)
    implementation(libs.retrofit)
    implementation(libs.material)
    implementation(libs.retrofit.gson)
    implementation(libs.hiltNavigationCompose)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")  // Make sure to use the version compatible with your project

    // Other test dependencies
    testImplementation("junit:junit:4.13.2")
    //    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("io.mockk:mockk:1.13.16")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    testImplementation("com.google.truth:truth:1.1")
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    implementation("com.google.accompanist:accompanist-pager:0.30.1")
    implementation ("androidx.compose.foundation:foundation:1.7.7")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.28.0")
}

kapt {
    correctErrorTypes = true
}