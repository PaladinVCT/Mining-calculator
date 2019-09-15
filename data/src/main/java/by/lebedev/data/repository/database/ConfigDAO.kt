package by.lebedev.data.repository.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import by.lebedev.data.repository.database.entity.Config
import io.reactivex.Single

@Dao
interface ConfigDAO {
    @Query("SELECT * FROM Config")
    fun getAll(): Single<ArrayList<Config>>

    @Insert
    fun insert(vararg config: Config)

    @Delete
    fun delete(config: Config)
}