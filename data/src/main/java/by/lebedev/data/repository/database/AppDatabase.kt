package by.lebedev.data.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.lebedev.data.repository.database.entity.Config

@Database(entities = arrayOf(Config::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun configDao(): ConfigDAO
}