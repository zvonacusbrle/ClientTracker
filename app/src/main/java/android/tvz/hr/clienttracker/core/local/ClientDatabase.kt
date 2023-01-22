package android.tvz.hr.clienttracker.core.local

import android.tvz.hr.clienttracker.core.local.entities.ClientDao
import android.tvz.hr.clienttracker.core.local.entities.ClientEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ClientEntity::class
    ],
    version = 1)

abstract class ClientDatabase:RoomDatabase(){
    abstract fun clientDao(): ClientDao
}
