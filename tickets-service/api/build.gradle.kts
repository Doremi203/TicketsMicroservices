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
    implementation(libs.spring.validation)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.reactor)
    //implementation("org.jetbrains.kotlin:kotlin-reflect")
    //testImplementation("org.springframework.boot:spring-boot-starter-test")
    //testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    //testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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