package nam.tran.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*


class ConvertData {

    var gson = Gson()

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}