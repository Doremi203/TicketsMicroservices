plugins {
}

dependencies {
    implementation(project(":authenticationService:bll"))
    implementation("org.springframework.boot:spring-boot-starter-security")
}