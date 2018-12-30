package dev.tran.nam.gobear.model

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

data class ArticleModel(
    val title: String = "",
    val description: String? = null,
    val date: Date? = null,
    val link: String? = null,
    val guid: String? = null
) : Serializable {
    fun getImage(): String? {
        description?.run {
            if (this.contains("src=\"")) {
                val list = this.split("src=\"")
                if (list.size == 2) {
                    if (list[1].contains("data-original=\"")) {
                        val list2 = list[1].split("data-original=\"")
                        if (list2.size == 2) {
                            return list2[1].split("\" >")[0]
                        }
                    } else
                        return list[1].split("\" >")[0]
                }
            }
        }
        return null
    }

    fun getDescriptionData(): String? {
        description?.run {
            if (this.contains("</br>")) {
                val list = this.split("</br>")
                if (list.size == 2) {
                    return list[1].split("]]>")[0]
                }
            }
        }
        return null
    }

    fun getDateData(): String? {
        date?.run {
            return SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).format(this)
        }
    }
}