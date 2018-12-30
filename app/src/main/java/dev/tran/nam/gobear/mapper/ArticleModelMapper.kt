/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.tran.nam.gobear.mapper

import dev.tran.nam.gobear.model.ArticleModel
import nam.tran.data.model.Article
import nam.tran.data.model.core.state.Resource
import java.util.*
import javax.inject.Inject

/**
 * Mapper class used to transform [Article] (in the  in domain layer)
 * to [ArticleModel] in the
 *  app layer.
 */
class ArticleModelMapper @Inject constructor() {

    /**
     * Transform a [Article] into an [ArticleModel].
     *
     * @param data Object to be transformed.
     * @return [ArticleModel].
     */
    fun transform(data: Article?): ArticleModel {
        data?.let {
            return ArticleModel(it.title,it.description,it.date,it.link,it.guid)
        }
    }

    /**
     * Transform a Collection of [Article] into a Collection of [ArticleModel].
     *
     * @param datas Objects to be transformed.
     * @return List of [ArticleModel].
     */
    fun transformModel(datas: List<Article>?): List<ArticleModel> {
        val dataModels: MutableList<ArticleModel>

        if (datas != null && !datas.isEmpty()) {
            dataModels = ArrayList()
            for (data in datas) {
                dataModels.add(transform(data))
            }
        } else {
            dataModels = ArrayList()
        }

        return dataModels
    }


    fun transform(data: Resource<List<Article>>): Resource<List<ArticleModel>> {
        return Resource(data.status, transformModel(data.data), data.errorResource, data.loading, data.retry)
    }
}
