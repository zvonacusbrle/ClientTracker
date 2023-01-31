package android.tvz.hr.clienttracker.data.local.database

import android.tvz.hr.clienttracker.data.local.entities.ClientDao
import android.tvz.hr.clienttracker.data.local.entities.ClientEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ClientEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class ClientDatabase:RoomDatabase(){
    abstract fun clientDao(): ClientDao
}
