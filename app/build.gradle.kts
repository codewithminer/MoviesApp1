plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.moviesapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.moviesapp"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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
    implementation(libs.firebase.database)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("com.github.bumptech.glide:glide:4.13.0")    //Glide is an image loading and caching library for Android.
    implementation("com.google.code.gson:gson:2.10.1")          //Gson is a library to convert Java objects to JSON and vice versa
    implementation("io.coil-kt:coil-compose:2.2.2")     //Coil is another image loading library, similar to Glide, but designed with Kotlin and Jetpack Compose in mind.
    implementation("androidx.compose.foundation:foundation:1.5.0")  //This is part of Jetpack Compose, Android’s modern toolkit for building UIs.
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.x.x.")   //This provides lifecycle-related extensions for Kotlin, which simplify lifecycle management (like LiveData and ViewModel interactions) in your app, making it easier to work with Android's lifecycle components.
    implementation("androidx.compose.runtime:runtime-livedata:x.x.x")   //This dependency integrates LiveData with Jetpack Compose. It ensures that Compose UI components react to changes in LiveData objects.
    implementation("com.google.accompanist:accompanist-pager:0.28.0")   //Accompanist Pager is a library that adds support for displaying swipeable pages in Jetpack Compose, much like ViewPager
    implementation("com.google.accompanist:accompanist-pager-indicators:0.28.0")  //This library works with Accompanist Pager to add indicators (like dots or other types of progress indicators) that show the current page in a swipeable view
}