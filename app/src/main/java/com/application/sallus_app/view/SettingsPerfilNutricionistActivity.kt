package com.application.sallus_app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivitySettingsPerfilNutricionistaBinding
import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.model.PerfilData
import com.application.sallus_app.viewmodel.NutritionistViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsPerfilNutricionistActivity : AppCompatActivity() {

    private val nutricionistViewModel: NutritionistViewModel by viewModel()
    private lateinit var binding: ActivitySettingsPerfilNutricionistaBinding
    private lateinit var dadosNutricionista : NutritionistData;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsPerfilNutricionistaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val dadosNutri = intent.getStringExtra("nutricionistaDataPerfil")
        dadosNutricionista = tratarNutricionistaJsonToData(dadosNutri!!)

        binding.includeToolbarSettings.textviewToolbarSettings.text = "Perfil"
        binding.nomeUsuario.text = dadosNutricionista.nome
        binding.edittextEmailPacienteSettings.setText(dadosNutricionista.email)
        binding.edittextNomeCompleto.setText(dadosNutricionista.nome)
        binding.edittextSenhaPacienteSettings.setText(dadosNutricionista.senha)

        val activity = this
        binding.includeToolbarSettings.buttonBackToolbarSettings.setOnClickListener {
            activity.finish()
        }

        binding.btnVoltar.setOnClickListener {
            activity.finish()
        }

//        binding.btnRedefinirSenha.setOnClickListener {
//            activity.finish()
//            val intent = Intent(this, SettingsPasswordNutricionistActivity::class.java)
//            startActivity(intent)
//        }

        binding.btnSalvarAlteracao.setOnClickListener {
            val inputEditTextNome = findViewById<EditText>(R.id.edittext_nome_completo)

            val nome = inputEditTextNome.text.toString()
            Log.i("CONTENT","NOME: $nome | ID: ${dadosNutricionista.id}")
            val data = PerfilData(dadosNutricionista.id, nome, dadosNutricionista.avatar)
            nutricionistViewModel.alterarDadosPerfil(data)
        }

    }

    fun tratarNutricionistaJsonToData(nutricionista: String): NutritionistData {
        val gson = Gson()
        val nutricionistaData: NutritionistData =
            gson.fromJson(nutricionista, NutritionistData::class.java)
        return nutricionistaData
    }
}