package com.example.aplicacioncontactos.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacioncontactos.R
import com.example.aplicacioncontactos.databinding.ItemDeListaDeCorreosBinding
import com.example.aplicacioncontactos.models.Email
import com.example.aplicacioncontactos.models.Emails


class CorreoListAdapter : RecyclerView.Adapter<CorreoListAdapter.CorreoItemViewHolder>() {

    private var correoLista: Emails = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CorreoItemViewHolder {
        val binding = ItemDeListaDeCorreosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CorreoItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return correoLista.size
    }

    override fun onBindViewHolder(holder: CorreoItemViewHolder, position: Int) {
        holder.bind(correoLista[position])
    }

    fun updateData(it: Emails) {
        correoLista = it
        notifyDataSetChanged()
    }

    class CorreoItemViewHolder(private val binding: ItemDeListaDeCorreosBinding) : RecyclerView.ViewHolder(binding.root) {

        private val lblItemDeCorreo: TextView = itemView.findViewById(R.id.lblItemDeCorreo)
        private val lblItemDeEtiquetaCorreo: TextView = itemView.findViewById(R.id.lblItemDeEtiquetaCorreo)

        fun bind(correo: Email) {
            lblItemDeCorreo.text = correo.email
            lblItemDeEtiquetaCorreo.text = correo.label
        }
    }
}
