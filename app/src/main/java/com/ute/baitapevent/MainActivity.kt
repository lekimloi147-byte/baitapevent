package com.ute.baitapevent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtName = findViewById<EditText>(R.id.edtName)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val cbFacebook = findViewById<CheckBox>(R.id.cbFacebook)
        val cbInstagram = findViewById<CheckBox>(R.id.cbInstagram)
        val cbTiktok = findViewById<CheckBox>(R.id.cbTiktok)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        edtName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                Toast.makeText(this, "Đang nhập vào ô Họ tên", Toast.LENGTH_SHORT).show()
            }
        }
        rgGender.setOnCheckedChangeListener { _, checkedId ->
            val genderText = when (checkedId) {
                R.id.rbMale -> "Nam"
                R.id.rbFemale -> "Nữ"
                R.id.rbOther -> "Khác"
                else -> ""
            }
            Toast.makeText(this, "Đã chọn giới tính: $genderText", Toast.LENGTH_SHORT).show()
        }

        val cbListener = { view: CheckBox, name: String ->
            if (view.isChecked) {
                Toast.makeText(this, "Đã chọn: $name", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Đã bỏ chọn: $name", Toast.LENGTH_SHORT).show()
            }
        }

        cbFacebook.setOnCheckedChangeListener { _, _ -> cbListener(cbFacebook, "Facebook") }
        cbInstagram.setOnCheckedChangeListener { _, _ -> cbListener(cbInstagram, "Instagram") }
        cbTiktok.setOnCheckedChangeListener { _, _ -> cbListener(cbTiktok, "TikTok") }
        btnRegister.setOnClickListener {
            val name = edtName.text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập họ tên!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = when (selectedGenderId) {
                R.id.rbMale -> "Nam"
                R.id.rbFemale -> "Nữ"
                R.id.rbOther -> "Khác"
                else -> "Chưa chọn"
            }

            val socials = ArrayList<String>()
            if (cbFacebook.isChecked) socials.add("Facebook")
            if (cbInstagram.isChecked) socials.add("Instagram")
            if (cbTiktok.isChecked) socials.add("TikTok")
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_NAME", name)
                putExtra("EXTRA_GENDER", gender)
                putStringArrayListExtra("EXTRA_SOCIALS", socials)
            }
            startActivity(intent)
        }
    }
}