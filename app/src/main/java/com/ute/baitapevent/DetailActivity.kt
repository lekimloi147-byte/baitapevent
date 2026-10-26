package com.ute.baitapevent

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvName = findViewById<TextView>(R.id.tvDetailName)
        val tvEmail = findViewById<TextView>(R.id.tvDetailEmail)
        val tvPhone = findViewById<TextView>(R.id.tvDetailPhone)
        val tvGender = findViewById<TextView>(R.id.tvDetailGender)
        val tvSocials = findViewById<TextView>(R.id.tvDetailSocials)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Nhận dữ liệu
        val name = intent.getStringExtra("EXTRA_NAME")
        val email = intent.getStringExtra("EXTRA_EMAIL")
        val phone = intent.getStringExtra("EXTRA_PHONE")
        val gender = intent.getStringExtra("EXTRA_GENDER")
        val socials = intent.getStringArrayListExtra("EXTRA_SOCIALS")

        // Gán dữ liệu lên View
        tvName.text = "👤 Họ và tên: $name"
        tvEmail.text = "✉️ Email: $email"
        tvPhone.text = "📞 Số điện thoại: $phone"
        tvGender.text = "⚧️ Giới tính: $gender"

        val socialText = if (socials.isNullOrEmpty()) "Không có" else socials.joinToString(", ")
        tvSocials.text = "🌐 Mạng xã hội: $socialText"

        btnBack.setOnClickListener {
            finish()
        }
    }
}