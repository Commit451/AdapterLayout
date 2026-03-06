import com.vanniktech.maven.publish.AndroidSingleVariantLibrary
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("com.android.library")
    id("com.vanniktech.maven.publish")
}

group = findProperty("GROUP") as String
version = findProperty("VERSION_NAME") as String

android {
    namespace = "com.commit451.adapterlayout.kotlin"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
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
    compileOnly(project(":adapterlayout"))
}

mavenPublishing {
    configure(AndroidSingleVariantLibrary("release", true, true))
    coordinates("com.commit451.adapterlayout", "adapterlayout-kotlin", version.toString())
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    if (System.getenv("RELEASE_SIGNING_ENABLED") == "true") {
        signAllPublications()
    }
}
