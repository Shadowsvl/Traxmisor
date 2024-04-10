plugins {
    id("arch.android.feature")
}

android {
    namespace = "com.traxion.home"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}