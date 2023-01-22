package android.tvz.hr.clienttracker.core.local.entities

import android.tvz.hr.clienttracker.core.domain.model.Client
import androidx.room.*

@Dao
interface ClientDao {

    @Transaction
    @Query("SELECT * FROM ClientEntity")
    suspend fun getClients():List<Client>
}