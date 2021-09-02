package tw.roy.deng.codility.utils.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TypesConverter {
    val gson = Gson()

    val type: Type = object : TypeToken<ArrayList<String?>?>() {}.type

    @TypeConverter
    fun fromString(value: String) : ArrayList<String> {
        return gson.fromJson(value, type);
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String>) : String {
        return gson.toJson(list)
    }
}