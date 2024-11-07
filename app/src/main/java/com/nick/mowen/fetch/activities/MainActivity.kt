package com.nick.mowen.fetch.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import com.nick.mowen.fetch.screens.ListingScreen
import com.nick.mowen.fetch.data.ListingViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ListingViewModel by viewModels<ListingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchListings()

        setContent {
            MaterialTheme {
                ListingScreen(viewModel.listings)
            }
        }
    }
}