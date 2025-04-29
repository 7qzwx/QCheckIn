
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

plugins {
    id("com.android.application")
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp") version "2.1.0-1.0.29"
}


android {
    namespace = "qzwx.app.qcheckin"
    compileSdk = 35

    defaultConfig {
        applicationId = "qzwx.app.qcheckin"
        minSdk = 29
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
        //自定义打包apk名称
 applicationVariants.all {
        val variant = this
        variant.outputs.map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                val outputFileName =
                    "QCheckIn${variant.versionName}_${
                        SimpleDateFormat(
                            "MMdd",
                            Locale.getDefault()
                        ).format(Date())
                    }.apk"
                output.outputFileName = outputFileName
            }
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
    ksp(libs.androidxRoomCompiler)
    implementation(project(":core"))
}