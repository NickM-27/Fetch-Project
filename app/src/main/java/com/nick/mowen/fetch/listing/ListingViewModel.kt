package com.nick.mowen.fetch.listing

import androidx.lifecycle.ViewModel
import com.nick.mowen.fetch.data.models.ListingData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListingViewModel : ViewModel() {

    private val _listings: MutableStateFlow<List<ListingData>> = MutableStateFlow(emptyList())
    val listings: StateFlow<List<ListingData>> = _listings.asStateFlow()
}