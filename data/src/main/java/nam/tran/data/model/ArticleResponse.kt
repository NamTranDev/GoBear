package nam.tran.data.model

data class ArticleResponse(val data : List<Article>? = null,
                           var isSuccess : Boolean = false, var msgError : String? = null, val errorCode : Int? = null)