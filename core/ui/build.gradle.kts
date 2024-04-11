plugins {
    id("arch.android.library")
    id("arch.android.library.compose")
}

android {
    namespace = "com.arch.ui"

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}

dependencies {
    implementation(project(":core:design-system"))
    implementation(project(":core:model"))

    implementation(libs.androidx.core.splashscreen)
}