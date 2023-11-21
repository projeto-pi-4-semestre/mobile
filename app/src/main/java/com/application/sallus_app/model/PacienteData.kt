package com.application.sallus_app.model

data class PacienteData(
    val nome: String,
    val email: String,
    val senha: String,
    val avaliacao: Double,
    val avatar: Int,
    val genero: String,
    val endereco: String,
    val contagemAvaliacao: Int,
    val telefone: String,
    val comorbidade: Boolean,
    val foto: List<String>?,
    val ativo: Boolean,
    val id: Long?
)