dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

rootProject.name = "authentication-service"

include("domain")
include("data")
include("bll")
include("api")