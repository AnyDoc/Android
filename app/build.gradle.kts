plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization") version "1.7.20"

}

android {
    namespace = "com.bocdoc.anydoc"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bocdoc.anydoc"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        dataBinding = true
        viewBinding = true
    }
    buildToolsVersion = "34.0.0"
}

dependencies {

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    // AndroidX
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.activity:activity-ktx:1.1.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.navigation:navigation-fragment:2.7.6")
    implementation("androidx.navigation:navigation-fragment-ktx:1.5.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

    // Material Design
    implementation("com.google.android.material:material:1.9.0")

    // Test Dependency
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation("junit:junit:4.13.2")

    // Third-Party
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    implementation("com.squareup.okhttp3:okhttp-bom:4.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("io.coil-kt:coil:2.3.0")
    implementation ("com.airbnb.android:lottie:3.7.0")

    // Google Map
    implementation ("com.google.android.gms:play-services-location:17.0.0")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
}