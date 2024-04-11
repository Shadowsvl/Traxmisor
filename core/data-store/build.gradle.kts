plugins {
    id("arch.android.library")
    id("arch.android.hilt")
}

android {
    namespace = "com.arch.data_store"

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}

dependencies {
    implementation(libs.datastore.preferences)
}