package nam.tran.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import nam.tran.data.model.Article

@Dao
interface ArticleDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list : List<Article>)

    @Query("Select * From article order by date")
    fun getArticle(): LiveData<List<Article>>
}