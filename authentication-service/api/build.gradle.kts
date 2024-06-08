plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.spring.plugin)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependencyManagement)
    application
}

dependencies {
    api(project(":bll"))

    implementation(libs.spring.openApi)
    implementation(libs.jackson.module.kotlin)
    implementation(libs.spring.webFlux)
    implementation(libs.spring.security)
    implementation(libs.spring.validation)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.reactor)
}

application {
    mainClass = "org.amogus.authenticationservice.api.ApplicationKt"
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

