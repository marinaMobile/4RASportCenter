package com.sportcenter.a4rasportcenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()

        val navView:NavigationView = findViewById(R.id.nv_view)

        toggle = ActionBarDrawerToggle(this,drawerActivity, R.string.open, R.string.close)
        drawerActivity.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.user_news ->{
                    Toast.makeText(this, "You are here!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.user -> {
                    Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()
                    true

                }
                R.id.post -> {
                    startActivity(Intent(this, PostActivity::class.java))
                    true
                }
                R.id.sign_out -> {
                    mAuth.signOut()
                    startActivity(Intent(this, LogReg::class.java))
                true
                }

                else -> false

            }
        }

    }
    private fun getNews() {
        val news: Call<News> = ServiceNews.newsInst.getArticle("in",  "sports")

        news.enqueue(object: Callback<News>
        {
            override fun onResponse(call: Call<News>, response: Response<News>) {

                val news: News? = response.body()
                Log.d("onResponse", "Ok")

                if(news!=null) {
                    newsRv.adapter = RVNews(this@MainActivity, news.articles)
                    newsRv.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("onFailure", "Bug")
            }
        })
    }
    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = mAuth.currentUser

        if (user==null) {
            val intent = Intent(this, LogReg::class.java)
            startActivity(intent)
        }
    }

}