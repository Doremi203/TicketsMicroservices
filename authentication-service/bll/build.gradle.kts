plugins {
    alias(libs.plugins.jib)
    alias(libs.plugins.jvm)
}

dependencies {
    api(project(":data"))

    implementation(libs.coroutines.reactor)
    implementation(libs.coroutines.core)
    implementation(libs.spring.security)
    implementation(libs.jwt.api)
    runtimeOnly(libs.jwt.impl)
    runtimeOnly(libs.jwt.jackson)
}