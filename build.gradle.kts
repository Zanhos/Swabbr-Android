buildscript {
    val kotlin_version by extra("1.4.21")
    repositories {
        jcenter()
        google()

    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("com.google.gms:google-services:${Versions.google_services}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.org_jetbrains_kotlin}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidx_navigation}")
        classpath("com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlytics}")

    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version Versions.io_gitlab_arturbosch_detekt
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.io_gitlab_arturbosch_detekt}")
}

allprojects {
    repositories {
        maven("https://plugins.gradle.org/m2/")
        maven("https://jitpack.io")
        maven("https://maven.google.com/")

        google()
        jcenter()
    }
}

task("clean") {
    delete(rootProject.buildDir)
}

tasks.withType<Wrapper> {
    distributionType = Wrapper.DistributionType.ALL
    // with buildSrcVersions
    gradleVersion = Versions.gradleLatestVersion
    // with refreshVersions
    gradleVersion = findProperty("gradleLatestVersion") as? String ?: gradle.gradleVersion
}

val detektAll by tasks.registering(io.gitlab.arturbosch.detekt.Detekt::class) {
    description = "Runs over whole code base without the starting overhead for each module."
    buildUponDefaultConfig = true
    autoCorrect = true
    parallel = true
    setSource(files(projectDir))
    config.setFrom(files("$rootDir/detekt.yml"))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/build/**")
    exclude("**/buildSrc/**")
    exclude("**/test/**/*.kt")
    reports {
        xml.enabled = false
        html.enabled = false
        txt.enabled = false
    }
}
