package android.tvz.hr.clienttracker.data.local.entities

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(client:ClientEntity)

    @Transaction
    @Query("SELECT * FROM ClientEntity")
    fun getClients(): Flow<List<ClientEntity>>

    @Query("SELECT * FROM ClientEntity WHERE id = :clientId")
    fun getClientsDetailsById(clientId: Int) : ClientEntity


}