package android.tvz.hr.clienttracker.data.domain.model

data class ClientDetails(
    val id: Int,
    val name: String,
    val age: Int,
    val picture: String? = null,
    val aboutUser: String? = null,
    val weight: List<Double> ?= null
)