// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.46.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
    }
}

plugins {
    id("com.android.application") version "8.1.0-beta03" apply false
    id("com.android.library") version "8.1.0-beta03" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}