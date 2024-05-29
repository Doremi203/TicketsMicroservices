dependencies {
    api(project(":authenticationService:domain"))

    implementation("org.liquibase:liquibase-core")
    implementation("org.ufoss.kotysa:kotysa-r2dbc:3.2.2")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
}