package com.nick.mowen.fetch.data.models

data class ListingData(val id: Int, val listId: Int, val name: String?) {

    fun isValid(): Boolean = name.isNullOrBlank().not()
}