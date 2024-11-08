package com.example.aplicacioncontactos.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacioncontactos.R
import com.example.aplicacioncontactos.databinding.ActivityListaDeTelefonosBinding
import com.example.aplicacioncontactos.ui.adapters.TelefonoListAdapter
import com.example.aplicacioncontactos.ui.viewmodels.PersonaListViewModel

class ListaDeTelefonosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaDeTelefonosBinding
    private val viewModel: PersonaListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityListaDeTelefonosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViewModelObservers()
        setupRecyclerView()

        val personaId = intent.getLongExtra("persona_id", -1L)
        viewModel.getTelefonoListByPersona(personaId.toInt())
    }

    private fun setupViewModelObservers() {
        viewModel.telefonoList.observe(this) { phones ->
            val adapter = binding.rvListaDeTelefonos.adapter as TelefonoListAdapter
            adapter.updateData(phones)
        }
    }

    private fun setupRecyclerView() {
        val adapter = TelefonoListAdapter()
        binding.rvListaDeTelefonos.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@ListaDeTelefonosActivity)
        }
    }
}