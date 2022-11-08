package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.api.NewsService
import com.example.newsapp.api.RetrofitHelper
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        // Fetch News form API
        getNews()
        firebaseAuth = FirebaseAuth.getInstance()

        // Logout
        img_logout.setOnClickListener {
            firebaseAuth.signOut()
            // Shared Preference
            val pref = getSharedPreferences("login", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putBoolean("flag", false)
            editor.apply()
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

    }


    private  fun getNews() {
        val newsAPI = RetrofitHelper.getInstance().create(NewsService::class.java)
        GlobalScope.launch(Dispatchers.Main){
            val result = newsAPI.getNews()
            val newsList = result.body()
            if (newsList != null) {
                adapter = NewsAdapter(this@NewsActivity,newsList.articles)
                recyclerview.adapter = adapter
                recyclerview.layoutManager = LinearLayoutManager(this@NewsActivity)
            }
        }
        // Search Functionality
        edt_search_news.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                GlobalScope.launch(Dispatchers.Main){
                    val result = p0?.let { newsAPI.searchNews(it) }
                    val newsList = result?.body()
                    if (newsList != null) {
                        adapter = NewsAdapter(this@NewsActivity,newsList.articles)
                        recyclerview.adapter = adapter
                        recyclerview.layoutManager = LinearLayoutManager(this@NewsActivity)
                    }
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                GlobalScope.launch(Dispatchers.Main){
                    val result = p0?.let { newsAPI.searchNews(it) }
                    val newsList = result?.body()
                    if (newsList != null) {
                        adapter = NewsAdapter(this@NewsActivity,newsList.articles)
                        recyclerview.adapter = adapter
                        recyclerview.layoutManager = LinearLayoutManager(this@NewsActivity)
                    }
                }
                return false
            }

        })

    }
}