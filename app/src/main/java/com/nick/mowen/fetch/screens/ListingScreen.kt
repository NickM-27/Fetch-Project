package com.nick.mowen.fetch.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nick.mowen.fetch.R
import com.nick.mowen.fetch.data.models.ListingData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.SortedMap

enum class ListMode {
    LIST,
    GRID
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingScreen(listingsFlow: StateFlow<SortedMap<Int, List<ListingData>>>) {
    val listings = listingsFlow.collectAsState()
    val scrollState = rememberScrollState()

    val listMode = remember { mutableStateOf(ListMode.LIST) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_fetch_project)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                actions = {
                    IconButton(
                        onClick = {
                            listMode.value = if (listMode.value == ListMode.LIST) ListMode.GRID else ListMode.LIST
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
                            contentDescription = "Toggle list mode"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            listings.value.forEach { (id, items) ->
                ListingRow(id, items, listMode.value)
            }
        }
    }
}

@Composable
fun ListingRow(id: Int, items: List<ListingData>, mode: ListMode) {
    val scrollState = rememberScrollState()

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(stringResource(R.string.template_listing, id), fontWeight = FontWeight.Bold)

        if (mode == ListMode.LIST) {
            Row(modifier = Modifier.horizontalScroll(scrollState), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items.forEach { item ->
                    ListingCard(item)
                }
            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier.size(800.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                columns = GridCells.Fixed(4)
            ) {
                items.forEach { item ->
                    item {
                        ListingCard(item)
                    }
                }
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
    val flow = MutableStateFlow(sortedMapOf(1 to listOf(ListingData(3, 1, "Item 3")), 2 to listOf(ListingData(4, 2, "Item 4"))))
    ListingScreen(flow.asStateFlow())
}