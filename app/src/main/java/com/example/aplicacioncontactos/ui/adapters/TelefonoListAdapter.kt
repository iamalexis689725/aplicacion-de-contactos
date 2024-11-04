package com.example.aplicacioncontactos.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacioncontactos.R
import com.example.aplicacioncontactos.databinding.ItemDeListaDeTelefonosBinding
import com.example.aplicacioncontactos.models.Phone
import com.example.aplicacioncontactos.models.Phones

class TelefonoListAdapter : RecyclerView.Adapter<TelefonoListAdapter.TelefonoItemViewHolder>() {

    private var telefonoLista: Phones = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TelefonoItemViewHolder {
        val binding = ItemDeListaDeTelefonosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TelefonoItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return telefonoLista.size
    }

    override fun onBindViewHolder(holder: TelefonoItemViewHolder, position: Int) {
        holder.bind(telefonoLista[position])
    }

    fun updateData(it: Phones) {
        telefonoLista = it
        notifyDataSetChanged()
    }

    class TelefonoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val lblItemDeNumeroDeTelefono: TextView = itemView.findViewById(R.id.lblItemDeNumeroDeTelefono)
        private val lblItemDeEtiqueteDeTelefono: TextView = itemView.findViewById(R.id.lblItemDeEtiqueteDeTelefono)

        fun bind(phone: Phone) {
            lblItemDeNumeroDeTelefono.text = phone.number
            lblItemDeEtiqueteDeTelefono.text = phone.label
        }
    }
}
