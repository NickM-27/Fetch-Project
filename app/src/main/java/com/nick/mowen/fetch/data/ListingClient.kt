package com.nick.mowen.fetch.data

import com.nick.mowen.fetch.data.models.ListingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.SortedMap

class ListingClient {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
    private val client: PrivateClient =
        Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
            .create(PrivateClient::class.java)

    suspend fun getHiringListings(): SortedMap<Int, List<ListingData>>? = withContext(Dispatchers.Default) {
        try {
            client.getHiringListings().execute().body()
                ?.filter { item -> item.isValid() }
                ?.sortedBy { it.name }?.groupBy { it.listId }
                ?.toSortedMap()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private interface PrivateClient {

        @GET("hiring.json")
        fun getHiringListings(): Call<List<ListingData>>
    }

    companion object {

        private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
    }
}