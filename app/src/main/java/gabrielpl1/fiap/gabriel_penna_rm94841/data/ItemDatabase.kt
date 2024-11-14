package gabrielpl1.fiap.gabriel_penna_rm94841.data

import androidx.room.Database
import androidx.room.RoomDatabase
import gabrielpl1.fiap.gabriel_penna_rm94841.model.ItemModel

@Database(entities = [ItemModel::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}