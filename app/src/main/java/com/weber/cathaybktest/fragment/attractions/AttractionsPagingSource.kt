package com.weber.cathaybktest.fragment.attractions

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.weber.cathaybktest.model.AttractionsData
import com.weber.cathaybktest.model.AttractionsDetail
import com.weber.cathaybktest.network.TravelTaipeiApi

class AttractionsPagingSource(private val api: TravelTaipeiApi, val lang: String) :
    PagingSource<Int, AttractionsDetail>() {

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AttractionsDetail> {

        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = api.getAttractions(lang, page)
            val repos = response.body() as AttractionsData
            val data = repos.attractionsDetailList
            val nextKey = if (repos.total != 0) page + 1 else null

            LoadResult.Page(data = data, prevKey = null, nextKey = nextKey)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AttractionsDetail>): Int? {
        return state.anchorPosition
    }
}