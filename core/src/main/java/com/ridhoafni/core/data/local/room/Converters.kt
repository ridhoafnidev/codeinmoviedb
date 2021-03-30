package com.ridhoafni.core.data.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ridhoafni.core.data.remote.response.Genre

class Converters{
    private val gson = Gson()

    @TypeConverter
    fun fromGenresList(genres: List<Genre?>?): String? {
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toGenresList(genres: String?): List<Genre?>? {
        if (genres == null) {
            return emptyList<Genre>()
        }
        val listType = object : TypeToken<List<Genre?>?>() {}.type
        return gson.fromJson<List<Genre?>>(genres, listType)
    }
}
