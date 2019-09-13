package by.lebedev.data.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Config(
    @PrimaryKey
    val id: Long,
    val name: String,
    val numberDevices: ArrayList<Int>
)
