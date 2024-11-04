package com.example.aplicacioncontactos.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacioncontactos.R
import com.example.aplicacioncontactos.databinding.PersonaItemLayoutBinding
import com.example.aplicacioncontactos.models.Persona
import com.example.aplicacioncontactos.models.Personas

class PersonaListAdapter(private val listener: PersonaItemListener) : RecyclerView.Adapter<PersonaListAdapter.PersonaItemViewHolder>() {

    private var personaList: Personas = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaItemViewHolder {
        val binding = PersonaItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonaItemViewHolder(binding.root, listener)
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    override fun onBindViewHolder(holder: PersonaItemViewHolder, position: Int) {
        holder.bind(personaList[position])
    }

    fun updateData(it: Personas) {
        personaList = it
        notifyDataSetChanged()
    }

    class PersonaItemViewHolder(itemView: View, private val listener: PersonaItemListener) : RecyclerView.ViewHolder(itemView) {
        private val lblPersonaItemTitle: TextView = itemView.findViewById(R.id.lblPersonaItemTitle)
        private val lblTelefonoPersona: TextView = itemView.findViewById(R.id.lblTelefonoPersona)

        fun bind(persona: Persona) {
            lblPersonaItemTitle.text = persona.name
            lblTelefonoPersona.text = persona.phones.firstOrNull()?.number ?: "No tiene teléfono"
            itemView.setOnClickListener {
                listener.onPersonaItemClicked(persona)
            }
            itemView.isLongClickable = true
            itemView.setOnLongClickListener {
                listener.onPersonaLongClicked(persona)
                itemView.showContextMenu()
                true
            }
        }

    }

    interface PersonaItemListener {
        fun onPersonaItemClicked(persona: Persona)
        fun onPersonaLongClicked(persona: Persona)
    }
}
