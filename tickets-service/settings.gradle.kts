dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

rootProject.name = "tickets-service"

include("domain")
include("data")
include("bll")
include("api")
