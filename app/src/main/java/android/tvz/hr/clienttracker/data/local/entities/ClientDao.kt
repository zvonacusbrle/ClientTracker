package android.tvz.hr.clienttracker.data.local.entities

import android.tvz.hr.clienttracker.data.domain.model.Client
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(client:ClientEntity)

    @Transaction
    @Query("SELECT * FROM ClientEntity")
    fun getClients(): Flow<List<ClientEntity>>


}