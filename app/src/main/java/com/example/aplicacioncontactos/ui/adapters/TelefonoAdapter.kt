package com.example.aplicacioncontactos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacioncontactos.databinding.TelefonoItemLayoutBinding

class TelefonoAdapter(private val phoneNumbers: List<String>) :
    RecyclerView.Adapter<TelefonoAdapter.TelefonoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TelefonoViewHolder {
        val binding = TelefonoItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TelefonoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TelefonoViewHolder, position: Int) {
        holder.bind(phoneNumbers[position])
    }

    override fun getItemCount(): Int = phoneNumbers.size

    class TelefonoViewHolder(private val binding: TelefonoItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(phoneNumber: String) {
            binding.lblItemNumero.text = phoneNumber
        }
    }
}
