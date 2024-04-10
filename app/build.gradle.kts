plugins {
    id("arch.android.application")
    id("arch.android.application.compose")
    id("arch.android.hilt")
}

android {
    namespace = "com.traxion.traxmisor"

    defaultConfig {
        applicationId = "com.traxion.traxmisor"
        versionCode = 1
        versionName = "1.0"

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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:design-system"))
    implementation(project(":core:ui"))

    implementation(project(":feature:home"))
    implementation(project(":feature:login"))
}