package com.suhel.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewmodel: MainViewmodel

    private val quoteText: TextView
        get() = findViewById(R.id.quoteText)

    private val author: TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewmodel= ViewModelProvider(this,MainViewmodelFactory(application)).get(MainViewmodel::class.java)


        setQuote(mainViewmodel.getQuote())
    }

    private fun setQuote(quote: Quotes) {
        quoteText.text=quote.text
        author.text=quote.author
    }

    fun onPrevious(view: View) {
        setQuote(mainViewmodel.previousQuote())
    }
    fun onNext(view: View) {
        setQuote(mainViewmodel.nextQuote())
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewmodel.getQuote().text)
        startActivity(Intent.createChooser(intent,mainViewmodel.getQuote().text))
//        startActivity(intent)
    }


}