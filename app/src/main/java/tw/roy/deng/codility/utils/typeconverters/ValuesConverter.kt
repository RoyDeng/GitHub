package tw.roy.deng.codility.utils.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ValuesConverter {
    val gson = Gson()

    val type: Type = object : TypeToken<List<Int?>?>() {}.type

    @TypeConverter
    fun fromInt(value: String) : List<Int> {
        return gson.fromJson(value, type);
    }

    @TypeConverter
    fun fromArrayList(list: List<Int>) : String? {
        return gson.toJson(list)
    }
}