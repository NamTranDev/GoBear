package dev.tran.nam.gobear.model

import java.util.*

data class ArticleModel(
    val title: String ="",
    val description: String? = null,
    val date: Date? = null,
    val link: String? = null,
    val guid: String? = null
)