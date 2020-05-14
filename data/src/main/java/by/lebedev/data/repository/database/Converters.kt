package by.lebedev.data.repository.database

import androidx.room.TypeConverter
import com.google.gson.Gson


class Converters {

    @TypeConverter
    fun listToJson(value: ArrayList<Int>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToArrayList(value: String): ArrayList<Int>? {
        val objects = Gson().fromJson(value, Array<Int>::class.java) as Array<Int>
        val arrayList = arrayListOf<Int>()
        arrayList.addAll(objects)
        return arrayList
    }
}