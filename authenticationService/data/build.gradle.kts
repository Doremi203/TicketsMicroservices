dependencies {
    api(project(":authenticationService:domain"))

    implementation("org.postgresql:r2dbc-postgresql")
}