package nam.tran.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nam.tran.data.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(ConvertData::class)
abstract class DbProvider : RoomDatabase() {
    abstract fun articleAccess(): ArticleDAO
}
