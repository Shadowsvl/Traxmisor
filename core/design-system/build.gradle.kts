plugins {
    id("arch.android.library")
    id("arch.android.library.compose")
}

android {
    namespace = "com.arch.design_system"

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}

dependencies {
    implementation(libs.androidx.google.fonts)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.coil.compose)
}