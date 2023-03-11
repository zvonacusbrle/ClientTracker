package android.tvz.hr.clienttracker.data.remote.model

data class ClientResponse(
    val id: Int,
    val name: String,
    val age: Int,
    val picture: String? = null,
    val aboutUser: String? = null,
)
