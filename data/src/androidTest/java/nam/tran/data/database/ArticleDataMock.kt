package nam.tran.data.database

import nam.tran.data.model.Article
import java.util.*

object ArticleDataMock {

    fun ceateArticle(): List<Article> {
        val datas = mutableListOf<Article>()
        datas.add(Article(
            "Bốn thuyền viên Philippines bị nạn trên biển Khánh Hòa",
            "<![CDATA[\n" +
                    "<a href=\"https://vnexpress.net/thoi-su/bon-thuyen-vien-philippines-bi-nan-tren-bien-khanh-hoa-3861569.html\">" +
                    "<img width=130 height=100 src=\"https://i-vnexpress.vnecdn.net/2018/12/30/1cuuthuyenviennuocngoai-154614-8246-2510-1546145922_180x108.jpg\" >" +
                    "</a></br>Sửa chữa thiết bị neo của tàu lúc biển động, sóng lớn, 4 thuyền viên đã không may bị ngã, trọng thương.\n" +
                    "]]",
            Date(System.currentTimeMillis()),
            "https://vnexpress.net/thoi-su/bon-thuyen-vien-philippines-bi-nan-tren-bien-khanh-hoa-3861569.html",
            "https://vnexpress.net/thoi-su/bon-thuyen-vien-philippines-bi-nan-tren-bien-khanh-hoa-3861569.html"
        ))
        return datas
    }
}