package gabrielpl1.fiap.gabriel_penna_rm94841.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import gabrielpl1.fiap.gabriel_penna_rm94841.model.ItemModel

@Dao
interface ItemDao {

    @Query("SELECT * FROM ItemModel")
    abstract fun getAll(): LiveData<List<ItemModel>>

    @Insert
    abstract fun insert(item: ItemModel)

}