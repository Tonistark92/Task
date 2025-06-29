plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
//    id("androidx.navigation.safeargs")

    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.iscoding.mytask"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.iscoding.mytask"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding =true

    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    //viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.1")

    // scalabe dp
    implementation ("com.intuit.sdp:sdp-android:1.1.1")
    // navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.9.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.9.0")
    //hilt
    implementation("com.google.dagger:hilt-android:2.56.2")
    kapt("com.google.dagger:hilt-android-compiler:2.56.2")
    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:3.0.0")
    implementation ("com.squareup.retrofit2:converter-gson:3.0.0")
    //life cycle
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.9.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")


    //coroutine testing
    androidTestImplementation ("androidx.compose.ui:uitest-junit4:1.8.3")
    testImplementation ("org.jetbrains.kotlinx:kotlinxcoroutines-test:1.5.0")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
}
//kapt {
//    correctErrorTypes = true
//}