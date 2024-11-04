package com.example.aplicacioncontactos.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacioncontactos.R
import com.example.aplicacioncontactos.databinding.ActivityTelefonoListBinding
import com.example.aplicacioncontactos.ui.adapters.TelefonoAdapter

class TelefonoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTelefonoListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTelefonoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val phoneNumbers = intent.getStringArrayListExtra("PHONE_NUMBERS") ?: arrayListOf()

        binding.rvListaTelefonos.apply {
            layoutManager = LinearLayoutManager(this@TelefonoListActivity)
            adapter = TelefonoAdapter(phoneNumbers)
        }
    }
}