dependencies {
    api(project(":authenticationService:domain"))

    implementation("io.r2dbc:r2dbc-pool")
    implementation("org.postgresql:r2dbc-postgresql")
    implementation("org.liquibase:liquibase-core")
}