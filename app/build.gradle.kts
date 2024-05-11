plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.apollographql.apollo3").version("3.8.2")
    id("com.google.gms.google-services")
}

android {
    namespace = "tech.mobile.social"
    compileSdk = 34

    defaultConfig {
        applicationId = "tech.mobile.social"
        minSdk = 29
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
}

dependencies {
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.49")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")

    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.apollographql.apollo3:apollo-runtime")
    implementation("com.apollographql.apollo3:apollo-adapters")

    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")

    val composeBom = platform("androidx.compose:compose-bom:2024.02.02")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    // Material Design 3
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.material3:material3-window-size-class")

    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.datastore:datastore-preferences:1.0.0")


    implementation("com.airbnb.android:lottie-compose:6.4.0")

    // FIRE BASE
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    implementation("com.google.firebase:firebase-auth-ktx:21.1.0")
    implementation("com.google.android.gms:play-services-auth:20.4.1")
    implementation("com.google.firebase:firebase-messaging:23.4.1")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

apollo {
    service("service") {
        packageName.set("tech.mobile.social")
//        mapScalar("DateTime", "java.util.Date", "com.apollographql.apollo3.adapter.DateAdapter")
        mapScalar(
            "DateTime",
            "java.time.LocalDateTime",
            "com.apollographql.apollo3.adapter.JavaLocalDateTimeAdapter"
        )
//        introspection {
//            endpointUrl.set("https://example.com/graphql")
//            headers.put("api-key", "1234567890abcdef")
//            schemaFile.set(file("src/main/graphql/schema.graphqls"))
//        }
    }
}

