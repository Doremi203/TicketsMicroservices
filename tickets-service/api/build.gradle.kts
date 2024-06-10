plugins {
    alias(libs.plugins.jib)
    alias(libs.plugins.jvm)
    alias(libs.plugins.spring.plugin)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependencyManagement)
    application
}

dependencies {
    api(project(":bll"))

    implementation(libs.spring.openApi)
    implementation(libs.spring.actuator)
    implementation(libs.jackson.module.kotlin)
    implementation(libs.spring.webFlux)
    implementation(libs.spring.validation)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.reactor)
    implementation(libs.kotlin.reflect)
}

jib {
    from {
        image = "openjdk:21"
        platforms {
            platform {
                architecture = "amd64"
                os = "linux"
            }
            platform {
                architecture = "arm64"
                os = "linux"
            }
        }
    }
    to {
        image = "tickets-service"
    }
    container {
        mainClass = "org.amogus.ticketsservice.api.ApplicationKt"
    }
}

application {
    mainClass = "org.amogus.ticketsservice.api.ApplicationKt"
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
