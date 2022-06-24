package com.sportcenter.a4rasportcenter

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.*

class Invitation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invitation)
        val txtEr: TextView = findViewById(R.id.txtMainMain)

        runBlocking {

            val job: Job = GlobalScope.launch(Dispatchers.IO) {
                getAsync(applicationContext)
            }
            job.join()
            val jsoup: String? = Hawk.get(Const.asyncResult, "")
            Log.d("cora", "cora $jsoup")

            txtEr.text = jsoup

            if (jsoup == "4x8V") {
                Intent(applicationContext, LogReg::class.java).also { startActivity(it) }
            } else {
                Intent(applicationContext, BrAct::class.java).also { startActivity(it) }
            }
            finish()
        }
    }

    private suspend fun getAsync(context: Context) {
        val asyncKey = Async(context)
        val asyncResult = asyncKey.getDocSecretKey()
        Hawk.put(Const.asyncResult, asyncResult)
    }
}