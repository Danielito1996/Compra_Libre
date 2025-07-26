plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinSerialization)
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.elitec.compralibre"
    compileSdk = 35

    defaultConfig {
        ndk {
            // On Apple silicon, you can omit x86_64.
            abiFilters += listOf("arm64-v8a", "armeabi-v7a")
        }
        applicationId = "com.elitec.compralibre"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1"

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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.cio)
    //implementation(libs.ktor.serialization.kotlinx.xml)
    implementation(libs.ktor.serialization.kotlinx.json) { //Esto es para evitar conflictos con el R8
        exclude(group = "com.fasterxml.jackson.core")
        exclude(group = "com.fasterxml.jackson.dataformat", module = "jackson-dataformat-xml")
    }
    implementation(libs.ktor.client.logs)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.websocket)
    // MAterial 3 Expressive
    implementation(libs.androidx.material3.expressive)
    //IConos
    implementation(libs.composeIcons.fontAwesome)
    //Navegacion
    implementation(libs.androidx.navigation.compose)
    //Serialization
    implementation(libs.kotlinx.serialization.json)
    //Inyeccion de dependencias con Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    //Materia 3
    implementation(libs.androidx.material3)
    //Icons
    implementation(libs.composeIcons.extension)
    // Permission
    implementation("com.google.accompanist:accompanist-permissions:0.37.3")
    //RoomDB
    implementation("androidx.room:room-runtime:2.7.2")
    implementation("androidx.room:room-ktx:2.7.2")
    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:2.7.2")
    ksp("androidx.room:room-compiler:2.7.2")

    implementation(libs.java.jwt)

    implementation(libs.appwrite)

    implementation("androidx.credentials:credentials:1.5.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.5.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    //Material3 Adaptative
    implementation(libs.androidx.materia3.adaptative)

    implementation ("com.google.android.gms:play-services-auth:21.4.0")
    implementation("io.github.mirzemehdi:kmpauth-google:2.4.0-alpha02")

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}