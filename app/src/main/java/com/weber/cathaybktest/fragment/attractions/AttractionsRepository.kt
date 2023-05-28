package com.weber.cathaybktest.fragment.attractions

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.weber.cathaybktest.model.AttractionsDetail
import com.weber.cathaybktest.network.TravelTaipeiApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AttractionsRepository @Inject constructor(private val api: TravelTaipeiApi) {

    fun getAttractions(lang: String): Flow<PagingData<AttractionsDetail>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = true,
                pageSize = 1
            ), pagingSourceFactory = {
                AttractionsPagingSource(api, lang)
            }
        ).flow
    }
}