package com.nick.mowen.fetch.listing

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.nick.mowen.fetch.data.models.ListingData
import kotlinx.coroutines.flow.StateFlow
import java.util.SortedMap

@Composable
fun ListingScreen(listingsFlow: StateFlow<SortedMap<Int, List<ListingData>>>) {
    val listings = listingsFlow.collectAsState()

    Column {

    }
}

@Composable
fun ListingRow(id: Int, items: List<ListingData>) {

}

@Composable
fun ListingCard(data: ListingData) {

}

@Composable
@Preview
fun ListingScreenPreview() {

}