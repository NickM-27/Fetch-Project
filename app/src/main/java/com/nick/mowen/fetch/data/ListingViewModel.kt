package com.nick.mowen.fetch.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nick.mowen.fetch.data.models.ListingData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.SortedMap

class ListingViewModel : ViewModel() {

    private val client: ListingClient = ListingClient()

    private val _listings: MutableStateFlow<SortedMap<Int, List<ListingData>>> = MutableStateFlow(sortedMapOf())
    val listings: StateFlow<SortedMap<Int, List<ListingData>>> = _listings.asStateFlow()

    fun fetchListings() {
        viewModelScope.launch {
            client.getHiringListings()?.let { listings ->
                _listings.value = listings
            }
        }
    }
}