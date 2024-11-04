package com.example.aplicacioncontactos.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplicacioncontactos.models.Persona
import com.example.aplicacioncontactos.models.Personas
import com.example.aplicacioncontactos.models.Phone
import com.example.aplicacioncontactos.models.Phones
import com.example.aplicacioncontactos.repositories.PersonaRepository

class PersonaListViewModel: ViewModel() {

    private val _personaList = MutableLiveData<Personas>().apply {
        value = arrayListOf()
    }
    val personaList: LiveData<Personas> = _personaList

    private val _persona = MutableLiveData<Persona>()
    val persona: LiveData<Persona> = _persona

    private val _telefonoList = MutableLiveData<Phones>().apply {
        value = arrayListOf()
    }
    val telefonoList: LiveData<Phones> = _telefonoList

    fun getPersonaList() {
        PersonaRepository.getPersonaList(
            onSuccess = {
                _personaList.value = it
            }, onError = {
                it.printStackTrace()
            }
        )
    }

    fun getTelefonoListByPersona(id: Int) {
        PersonaRepository.getPersonaById(
            id,
            onSuccess = {
                _telefonoList.value = it.phones
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

    fun addPersona(persona: Persona, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        PersonaRepository.addPersona(
            persona,
            onSuccess = {
                getPersonaList()
                onSuccess()
            },
            onError = { onError(it) }
        )
    }

    fun updatePersona(persona: Persona, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        PersonaRepository.updatePersona(
            persona,
            onSuccess = { onSuccess() },
            onError = { onError(it) }
        )
    }

    fun deletePersona(persona: Persona) {
        PersonaRepository.deletePersona(
            persona,
            onSuccess = {
                getPersonaList()
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

    fun getPersonaById(id: Int, onSuccess: (Persona) -> Unit, onError: (Throwable) -> Unit) {
        PersonaRepository.getPersonaById(
            id,
            onSuccess = { onSuccess(it) },
            onError = { onError(it) }
        )
    }

    fun obtenerPersonaCompleta(persona: Persona) {
        PersonaRepository.obtenerPersona(persona,
            onSuccess = {
                getPersonaList()
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}