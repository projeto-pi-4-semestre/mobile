package com.application.sallus_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.repository.RetrofitRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class PacienteViewModel : ViewModel() {
    private val repository = RetrofitRepository()

    private val _listaTodosPacientes = MutableLiveData<List<PacienteData>>()
    val listaTodosPacientes: MutableLiveData<List<PacienteData>> = _listaTodosPacientes

    fun fetchTodosPacientes() {
        viewModelScope.launch {
            try {
                val todosPacientes = repository.apiServicePaciente.getTodosPacientes()
                _listaTodosPacientes.postValue(todosPacientes)
                Log.i(
                    "logTodosPacientes",
                    "fetchTodosPacientes: lista de todos pacientes: $todosPacientes"
                )
            } catch (e: Exception) {
                Log.i("ERROR_FETCH_PATIENT", "fetchTodosPacientes: algo inesperado aconteceu")
            }
        }
    }

    fun addingNewPaciente(novoPaciente: PacienteData) {
        viewModelScope.launch {
            println(novoPaciente)
            try {
                val response = repository.apiServicePaciente.adicionarCliente(novoPaciente)
                Log.i(
                    "logAddingPaciente",
                    "makeNewPaciente: paciente cadastrado com sucesso! $novoPaciente"
                )
            } catch (e: Exception) {
                Log.i(
                    "logAddingNewPaciente",
                    "makeNewPaciente: ocorreu algum erro ao cadastrar novo paciente $e"
                )
            }
        }
    }

}