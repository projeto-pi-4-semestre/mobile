package com.application.sallus_app.service

import com.application.sallus_app.model.LoginData
import com.application.sallus_app.model.UsuarioData
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("clientes/login")
    suspend fun loginPaciente(@Body dadosPaciente: UsuarioData): LoginData

    @POST("nutricionistas/login")
    suspend fun loginNutri(@Body dadosPaciente: UsuarioData): LoginData

}