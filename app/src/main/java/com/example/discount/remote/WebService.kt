package com.example.discount.remote

interface WebService {
    suspend fun getXMlString(url: String): String
}
