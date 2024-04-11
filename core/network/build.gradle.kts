plugins {
    id("arch.android.library")
    id("arch.android.hilt")
}

android {
    namespace = "com.arch.network"

    defaultConfig {
        buildConfigField("String", "API_BASE_URL", "\"https://webhook-test.com/\"")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    implementation(libs.bundles.retrofit)
}