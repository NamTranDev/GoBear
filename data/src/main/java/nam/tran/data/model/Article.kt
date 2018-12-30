package nam.tran.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import nam.tran.data.database.ConvertData
import java.util.*

@Entity(tableName = "Article")
data class Article(
    @PrimaryKey
    val title: String = "",
    val description: String? = null,
    @TypeConverters(ConvertData::class)
    val date: Date? = null,
    val link: String? = null,
    val guid: String? = null
)