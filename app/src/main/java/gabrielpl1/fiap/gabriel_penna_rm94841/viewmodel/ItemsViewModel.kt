package gabrielpl1.fiap.gabriel_penna_rm94841.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import gabrielpl1.fiap.gabriel_penna_rm94841.data.ItemDao
import gabrielpl1.fiap.gabriel_penna_rm94841.data.ItemDatabase
import gabrielpl1.fiap.gabriel_penna_rm94841.model.ItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ItemsViewModel(application: Application) : AndroidViewModel(application) {

    // Referência para o DAO que será usado para acessar o banco de dados.
    private val itemDao: ItemDao

    // LiveData que contém a lista de itens. A UI pode observar este LiveData para receber
    // atualizações quando os dados mudarem.
    val itemsLiveData: LiveData<List<ItemModel>>

    init {
        // Cria uma instância do banco de dados Room usando o método Room.databaseBuilder().
        // Este método recebe três parâmetros: o contexto da aplicação, a classe do banco de dados
        // e o nome do banco de dados.
        val database = Room.databaseBuilder(
            getApplication(),
            ItemDatabase::class.java,
            "items_database"
        ).build()

        // Obtém uma referência para o DAO chamando database.itemDao().
        // Esta referência será usada para acessar o banco de dados.
        itemDao = database.itemDao()

        // Inicializa o LiveData com a lista de itens do banco de dados chamando itemDao.getAll().
        itemsLiveData = itemDao.getAll()
    }
    fun addItem(item: String, item2: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Cria um novo ItemModel com o nome fornecido.
            val newItem = ItemModel(name = item, desc = item2)
            // Insere o novo item no banco de dados. Como esta operação pode levar algum tempo,
            // ela é executada em uma corrotina para evitar bloquear a thread principal.
            itemDao.insert(newItem)
        }
    }

}