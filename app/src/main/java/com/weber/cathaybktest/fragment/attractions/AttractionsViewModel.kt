package com.weber.cathaybktest.fragment.attractions

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.weber.cathaybktest.model.AttractionsDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AttractionsViewModel @Inject constructor(
    private val attractionsRepository: AttractionsRepository
) : ViewModel() {

    fun getAllAttractions(lang: String): Flow<PagingData<AttractionsDetail>> {
        return attractionsRepository.getAttractions(lang)
    }
}
