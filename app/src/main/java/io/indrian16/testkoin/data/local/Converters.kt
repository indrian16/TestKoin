package io.indrian16.testkoin.data.local

import android.arch.persistence.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestams(value: Long?) = value?.let {

        Date(it)
    }

    @TypeConverter
    fun dateToTimestams(date: Date?) = date?.time
}