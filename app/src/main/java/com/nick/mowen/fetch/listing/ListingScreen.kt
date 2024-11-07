package com.nick.mowen.fetch.listing

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nick.mowen.fetch.R
import com.nick.mowen.fetch.data.models.ListingData
import kotlinx.coroutines.flow.StateFlow
import java.util.SortedMap

@Composable
fun ListingScreen(listingsFlow: StateFlow<SortedMap<Int, List<ListingData>>>) {
    val listings = listingsFlow.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(24.dp)
            .verticalScroll(scrollState)
    ) {
        listings.value.forEach { (id, items) ->
            ListingRow(id, items)
        }
    }
}

@Composable
fun ListingRow(id: Int, items: List<ListingData>) {
    val scrollState = rememberScrollState()

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(stringResource(R.string.template_listing, id), fontWeight = FontWeight.Bold)
        Row(modifier = Modifier.horizontalScroll(scrollState), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items.forEach { item ->
                ListingCard(item)
            }
        }
    }
}

@Composable
fun ListingCard(data: ListingData) {
    Card {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(data.name.toString(), fontSize = 16.sp)
            Text(data.id.toString(), fontSize = 12.sp)
        }
    }
}

@Composable
@Preview
fun ListingScreenPreview() {

}