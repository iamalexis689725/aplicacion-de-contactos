package com.example.aplicacioncontactos.models

data class Email (
    val id: Long,
    val email: String,
    val personaID: Long,
    val label: String
)
typealias Emails = ArrayList<Email>