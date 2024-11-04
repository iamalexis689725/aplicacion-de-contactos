package com.example.aplicacioncontactos.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacioncontactos.R
import com.example.aplicacioncontactos.databinding.ActivityPersonaListBinding
import com.example.aplicacioncontactos.models.Persona
import com.example.aplicacioncontactos.ui.adapters.PersonaListAdapter
import com.example.aplicacioncontactos.ui.viewmodels.PersonaListViewModel

class PersonaListActivity : AppCompatActivity(), PersonaListAdapter.PersonaItemListener {

    private var selectedPersona: Persona? = null
    lateinit var binding: ActivityPersonaListBinding
    private val viewModel: PersonaListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonaListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViewModelObservers()
        setupEventListener()
        setupRecyclerView()
        viewModel.getPersonaList()

    }

    private fun setupEventListener() {
        binding.fabAddPersona.setOnClickListener {
            val intent = Intent(this, PersonaFormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        val adapter = PersonaListAdapter(this)
        binding.rvPersonaList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@PersonaListActivity)
        }
        registerForContextMenu(binding.rvPersonaList)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                selectedPersona?.let {
                    println("selected persona: $it")
                    viewModel.deletePersona(it)
                }
                true
            }
            R.id.action_insert_telefono -> {
                selectedPersona?.let { persona ->
                    println("persona seleccionada: $persona")
                    println("id de la persona seleccionada: ${persona.id}")
                    println("phone de la persona seleccionada: ${persona.phones}")

                    if (persona.phones.isNotEmpty()) {
                        val phoneNumbers = ArrayList(persona.phones.map { it.number })
                        val intent = Intent(this, TelefonoListActivity::class.java).apply {
                            putStringArrayListExtra("PHONE_NUMBERS", phoneNumbers)
                        }
                        startActivity(intent)
                    } else {
                        println("La persona seleccionada no tiene números de teléfono.")
                    }
                }
                true
            }
            R.id.action_lista -> {
                selectedPersona?.let { persona ->
                    val intent = Intent(this, ListaDeTelefonosActivity::class.java).apply {
                        putExtra("persona_id", persona.id)
                    }
                    startActivity(intent)
                }
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.personaList.observe(this) {
            val adapter = binding.rvPersonaList.adapter as PersonaListAdapter
            adapter.updateData(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPersonaList()
    }

    override fun onPersonaItemClicked(persona: Persona) {
        val intent = Intent(this, PersonaFormActivity::class.java).apply {
            putExtra("persona_id", persona.id)
            putExtra("persona_name", persona.name)
            putExtra("persona_last_name", persona.last_name)
            putExtra("persona_company", persona.company)
            putExtra("persona_address", persona.address)
            putExtra("persona_city", persona.city)
            putExtra("persona_state", persona.state)
        }
        startActivity(intent)
    }

    override fun onPersonaLongClicked(persona: Persona) {
        this.selectedPersona = persona
    }
}