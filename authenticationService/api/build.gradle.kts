plugins {
}

dependencies {
    implementation(project(":authenticationService:bll"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("io.projectreactor:reactor-test")
}