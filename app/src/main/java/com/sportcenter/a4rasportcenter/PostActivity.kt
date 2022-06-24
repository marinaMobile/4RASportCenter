package com.sportcenter.a4rasportcenter

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {
    private val pickImage = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)


        add_btn.setOnClickListener{

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, pickImage)

        }
        publish_btn.setOnClickListener {
            publishPost()
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == pickImage){
            image.setImageURI(data?.data)
        }
    }

    private fun publishPost() {
        val title = et_title.text.toString()

        when {
            TextUtils.isEmpty(title) -> {
                et_title.error = "Title is empty. Don't leave this field empty!"
                et_title.requestFocus()
            } else -> {
            Toast.makeText(this, "Thank you for your post!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
        }
    }
}