package com.example.aplicacioncontactos.api

import com.example.aplicacioncontactos.models.Persona
import com.example.aplicacioncontactos.models.Personas
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path


interface JSONPlaceHolderService2 {

    @GET("personas")
    fun getPersonasList(): Call<Personas>

    @GET("personas/{id}")
    fun getPersonaById(@Path("id") id: Int): Call<Persona>

    @GET("personas/{id}")
    fun obtenerPersona(@Path("id") id: Int): Call<Void>

    @POST("personas")
    fun addPersona(@Body persona: Persona): Call<Persona>

    @PUT("personas/{id}")
    fun updatePersona(@Path("id") id: Int, @Body persona: Persona): Call<Persona>

    @DELETE("personas/{id}")
    fun deletePersona(@Path("id") id: Int): Call<Void>

    @Multipart
    @POST("personas/{id}/profile-picture")
    fun uploadProfilePicture(
        @Path("id") id: Int,
        @Part file: MultipartBody.Part
    ): Call<Persona>

}