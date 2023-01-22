package android.tvz.hr.clienttracker.core.domain.model

data class Client(
    val id: Int,
    val name: String,
    val age: Int,
    val picture: String? = null,
    val aboutUser: String? = null,
)
