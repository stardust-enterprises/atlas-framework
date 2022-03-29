object Coordinates {
    const val name = "atlas-framework"
    const val desc = "Injection-based modular modding framework."
    const val vendor = "Stardust Enterprises"

    const val gitHost = "github.com"
    const val repoId = "stardust-enterprises/$name"

    const val group = "fr.stardustenterprises"
    const val version = "0.0.1"
}

object Pom {
    val licenses = arrayOf(
        License("ISC License", "https://opensource.org/licenses/ISC"),
    )

    val developers = arrayOf(
        Developer("xtrm"),
    )
}

data class License(
    val name: String,
    val url: String,
    val distribution: String = "repo"
)

data class Developer(val id: String, val name: String = id)
