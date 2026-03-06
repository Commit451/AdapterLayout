plugins {
    id("com.android.application")
}

android {
    namespace = "com.commit451.adapterlayout.sample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.commit451.adapterlayout.sample"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        abortOnError = false
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")

    implementation("com.wefika:flowlayout:0.4.1") {
        exclude(group = "com.intellij", module = "annotations")
    }

    implementation(project(":adapterlayout"))
    implementation(project(":adapterflowlayout"))
    implementation(project(":adapterlayout-kotlin"))
}
