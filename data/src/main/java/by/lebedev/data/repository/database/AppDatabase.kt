package by.lebedev.data.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.lebedev.data.repository.database.entity.ConfigResponse

@Database(entities = arrayOf(ConfigResponse::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun configDao(): ConfigDAO
}