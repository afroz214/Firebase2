package com.smartherd.firebase2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        btn_change_email.setOnClickListener {
            startActivity(Intent(this@TestActivity, ChangeEmail::class.java))
        }

        btn_profile.setOnClickListener {
            startActivity(Intent(this@TestActivity, ProfileActivity::class.java))
        }
        btn_user_info.setOnClickListener {
            startActivity(Intent(this@TestActivity, UserInformation::class.java))
        }
        image_uploade_btn.setOnClickListener {
            startActivity(Intent(this@TestActivity, StorageActivity::class.java))
        }
    }
}
