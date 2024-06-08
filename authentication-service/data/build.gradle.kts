plugins {
    alias(libs.plugins.jvm)
}

dependencies {
    api(project(":domain"))

    api(libs.liquibase.core)
    api(libs.kotysa.r2dbc)
    runtimeOnly(libs.postgres.r2dbc)
    runtimeOnly(libs.r2dbc.pool)
    runtimeOnly(libs.postgres.jdbc)
    runtimeOnly(libs.spring.jdbc)
}
