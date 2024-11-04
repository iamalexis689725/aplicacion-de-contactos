package com.example.aplicacioncontactos.repositories

import com.example.aplicacioncontactos.api.JSONPlaceHolderService2
import com.example.aplicacioncontactos.models.Persona
import com.example.aplicacioncontactos.models.Personas
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

object PersonaRepository {

    fun getPersonaList(onSuccess: (Personas) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository2.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService2::class.java)
        service.getPersonasList().enqueue(object : Callback<Personas> {
            override fun onResponse(call: Call<Personas>, response: Response<Personas>) {
                if (response.isSuccessful) {
                    val persona = response.body()
                    onSuccess(persona!!)
                }
            }

            override fun onFailure(call: Call<Personas>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun addPersona(persona: Persona, onSuccess: (Persona) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository2.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService2::class.java)
        service.addPersona(persona).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun updatePersona(persona: Persona, onSuccess: (Persona) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository2.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService2::class.java)
        service.updatePersona(persona.id!!.toInt(), persona).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    onSuccess(post!!)
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }
    fun deletePersona(persona: Persona, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository2.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService2::class.java)
        service.deletePersona(persona.id!!.toInt()).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onSuccess()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun obtenerPersona(persona: Persona, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository2.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService2::class.java)
        service.obtenerPersona(persona.id!!.toInt()).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onSuccess()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun getPersonaById(id: Int, onSuccess: (Persona) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository2.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService2::class.java)
        service.getPersonaById(id).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful && response.body() != null) {
                    val persona = response.body()!!
                    println("Persona recibida en body: $persona") // Esto imprime el contenido de persona
                    onSuccess(persona)
                } else {
                    println("Error en la respuesta: ${response.errorBody()?.string()}")
                    onError(RuntimeException("Failed to get persona by ID"))
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }


    fun uploadProfilePicture(id: Int, imageFile: File, onSuccess: (Persona) -> Unit, onError: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository2.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService2::class.java)

        val requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageFile)
        val body = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)

        service.uploadProfilePicture(id, body).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                if (response.isSuccessful) {
                    val persona = response.body()
                    onSuccess(persona!!)
                } else {
                    onError(RuntimeException("Error: ${response.errorBody()?.string()}"))
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                onError(t)
            }
        })
    }


}