package by.lebedev.data.repository.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import by.lebedev.data.repository.database.entity.ConfigResponse
import io.reactivex.Single

@Dao
interface ConfigDAO {
    @Query("SELECT * FROM ConfigResponse")
    fun getAll(): Single<ArrayList<ConfigResponse>>

    @Insert
    fun insert(vararg configResponse: ConfigResponse)

    @Delete
    fun delete(configResponse: ConfigResponse)
}