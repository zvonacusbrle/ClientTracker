package android.tvz.hr.clienttracker.core.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClientEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val age: Int,
    val picture: String? = null,
    val aboutUser: String? = null,
)
