package com.example.aplicacioncontactos.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacioncontactos.R
import com.example.aplicacioncontactos.databinding.ActivityListaDeCorreosBinding
import com.example.aplicacioncontactos.databinding.ActivityListaDeTelefonosBinding
import com.example.aplicacioncontactos.ui.adapters.CorreoListAdapter
import com.example.aplicacioncontactos.ui.adapters.TelefonoListAdapter
import com.example.aplicacioncontactos.ui.viewmodels.PersonaListViewModel

class ListaDeCorreosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaDeCorreosBinding
    private val viewModel: PersonaListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityListaDeCorreosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        setupViewModelObservers()
        setupRecyclerView()

        val personaId = intent.getLongExtra("persona_id", -1L)
        viewModel.getCorreoListByPersona(personaId.toInt())
    }


    private fun setupViewModelObservers() {
        viewModel.correoList.observe(this) { emails ->
            val adapter = binding.rvListaDeCorreos.adapter as CorreoListAdapter
            adapter.updateData(emails)
        }
    }

    private fun setupRecyclerView() {
        val adapter = CorreoListAdapter()
        binding.rvListaDeCorreos.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@ListaDeCorreosActivity)
        }
    }
}