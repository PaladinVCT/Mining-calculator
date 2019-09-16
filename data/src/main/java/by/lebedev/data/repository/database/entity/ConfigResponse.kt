package by.lebedev.data.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ConfigResponse(
    @PrimaryKey
    val id: Long,
    val name: String,
    val vendor :String,
    val numberDevices: ArrayList<Int>
)
