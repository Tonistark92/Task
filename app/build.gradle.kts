plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // use this
    id("androidx.navigation.safeargs.kotlin")
    //don use this ******
//    id("androidx.navigation.safeargs")
//    id("com.google.devtools.ksp")

//    kotlin("kapt")
//    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.iscoding.mytask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.iscoding.mytask"
        minSdk = 24
        targetSdk = 34
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
    //for ksp
//    applicationVariants.all { variant ->
//        val isDebug = variant.buildType.name == "debug"
//
//        kotlin {
//            sourceSets {
//                getByName(variant.name) {
//                    kotlin.srcDir("build/generated/ksp/${variant.name}/kotlin")
//                }
//            }
//        }
//
//        // Example condition that returns Boolean
//        isDebug
//    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    //viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")

    // scalabe dp
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    // navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")
    //hilt
//    implementation("com.google.dagger:hilt-android:2.48")
//    implementation("androidx.compose.runtime:runtime-android:1.6.8")
//    kapt("com.google.dagger:hilt-android-compiler:2.48")
    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //life cycle
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.2")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//    // koin
//    implementation ("io.insert-koin:koin-android:3.5.3")
//    implementation ("io.insert-koin:koin-core:3.5.3")
//    //koin for compose
//    implementation ("io.insert-koin:koin-androidx-compose:3.5.3")
//
//    implementation ("io.insert-koin:koin-annotations:1.3.1")
////    ksp ("io.insert-koin:koin-ksp-compiler:3.2.0")
    implementation ("io.insert-koin:koin-android:3.5.3")
    implementation ("io.insert-koin:koin-androidx-navigation:3.2.0-beta-1")
    implementation ("io.insert-koin:koin-androidx-compose:3.5.3")
    testImplementation ("io.insert-koin:koin-test-junit4:3.2.0-beta-1")
}
//kapt {
//    correctErrorTypes = true
//}