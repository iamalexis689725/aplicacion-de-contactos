package com.example.aplicacioncontactos.models

data class Persona (
    val name: String,
    val last_name: String,
    val company: String,
    val address: String,
    val city: String,
    val state: String,
    val phones: ArrayList<Phone> = arrayListOf(),
    val emails: ArrayList<Email> = arrayListOf()
) {
    var id: Long? = null
}
typealias Personas = ArrayList<Persona>