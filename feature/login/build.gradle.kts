plugins {
    id("arch.android.feature")
}

android {
    namespace = "com.traxion.login"

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}