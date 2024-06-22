package com.suhel.quotesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson


class MainViewmodel(var context: Context): ViewModel() {

    private var quotesList: Array<Quotes> = emptyArray()
    private var index=0

    init {
        quotesList= loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Quotes> {

        val inputStream= context.assets.open("quotes.json")
        val size:Int= inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json= String(buffer, Charsets.UTF_8)
        val gson=Gson()
        return gson.fromJson(json, Array<Quotes>::class.java)

    }

    fun getQuote()= quotesList[index]

    fun nextQuote()= quotesList[++index % quotesList.size]

    fun previousQuote()= quotesList[(--index + quotesList.size) % quotesList.size]
}