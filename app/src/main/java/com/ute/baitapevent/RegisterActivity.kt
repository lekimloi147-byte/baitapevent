package com.ute.baitapevent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val edtName = findViewById<TextInputEditText>(R.id.edtName)
        val edtEmail = findViewById<TextInputEditText>(R.id.edtEmail)
        val edtPhone = findViewById<TextInputEditText>(R.id.edtPhone)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val cbFacebook = findViewById<CheckBox>(R.id.cbFacebook)
        val cbInstagram = findViewById<CheckBox>(R.id.cbInstagram)
        val cbTiktok = findViewById<CheckBox>(R.id.cbTiktok)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val imgAvatar = findViewById<ImageView>(R.id.imgAvatar)

        // 1. Toast sự kiện tương tác ảnh đại diện
        imgAvatar.setOnClickListener {
            Toast.makeText(this, "Tính năng chọn ảnh đại diện đang phát triển", Toast.LENGTH_SHORT).show()
        }

        // 2. Toast thông báo focus vào các EditText
        edtName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) Toast.makeText(this, "Đang nhập ô Họ tên", Toast.LENGTH_SHORT).show()
        }
        edtEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) Toast.makeText(this, "Đang nhập ô Email", Toast.LENGTH_SHORT).show()
        }
        edtPhone.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) Toast.makeText(this, "Đang nhập ô Số điện thoại", Toast.LENGTH_SHORT).show()
        }

        // 3. Sự kiện RadioButton Giới tính
        rgGender.setOnCheckedChangeListener { _, checkedId ->
            val gender = when (checkedId) {
                R.id.rbMale -> "Nam"
                R.id.rbFemale -> "Nữ"
                R.id.rbOther -> "Khác"
                else -> ""
            }
            Toast.makeText(this, "Đã chọn giới tính: $gender", Toast.LENGTH_SHORT).show()
        }

        // 4. Sự kiện CheckBox Mạng xã hội
        val cbListener = { name: String, isChecked: Boolean ->
            val status = if (isChecked) "Chọn" else "Bỏ chọn"
            Toast.makeText(this, "Đã $status mạng xã hội: $name", Toast.LENGTH_SHORT).show()
        }

        cbFacebook.setOnCheckedChangeListener { _, isChecked -> cbListener("Facebook", isChecked) }
        cbInstagram.setOnCheckedChangeListener { _, isChecked -> cbListener("Instagram", isChecked) }
        cbTiktok.setOnCheckedChangeListener { _, isChecked -> cbListener("TikTok", isChecked) }

        // 5. Sự kiện nút Đăng ký kiểm tra dữ liệu và chuyển màn hình
        btnRegister.setOnClickListener {
            val name = edtName.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val phone = edtPhone.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val genderId = rgGender.checkedRadioButtonId
            val gender = when (genderId) {
                R.id.rbMale -> "Nam"
                R.id.rbFemale -> "Nữ"
                R.id.rbOther -> "Khác"
                else -> "Chưa chọn"
            }

            val socials = ArrayList<String>()
            if (cbFacebook.isChecked) socials.add("Facebook")
            if (cbInstagram.isChecked) socials.add("Instagram")
            if (cbTiktok.isChecked) socials.add("TikTok")

            // Chuyển dữ liệu sang màn hình chi tiết
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_NAME", name)
                putExtra("EXTRA_EMAIL", email)
                putExtra("EXTRA_PHONE", phone)
                putExtra("EXTRA_GENDER", gender)
                putStringArrayListExtra("EXTRA_SOCIALS", socials)
            }
            startActivity(intent)
        }
    }
}