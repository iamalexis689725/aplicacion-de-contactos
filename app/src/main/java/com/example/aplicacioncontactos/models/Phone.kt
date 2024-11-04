package com.example.aplicacioncontactos.models

data class Phone (
    val id: Long,
    val number: String,
    val personaID: Long,
    val label: String
)
typealias Phones = ArrayList<Phone>