plugins {
    alias(libs.plugins.jvm)
}

dependencies {
    api(project(":domain"))

    implementation(libs.liquibase.core)
    implementation(libs.kotysa.r2dbc)
    runtimeOnly(libs.postgres.r2dbc)
}

repositories {
    mavenCentral()
}
