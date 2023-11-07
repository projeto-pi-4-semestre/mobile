import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentCadastroBinding
import com.application.sallus_app.view.fragments.FragmentComorbidade

class FragmentDadosPessoais : Fragment() {
    private lateinit var binding: FragmentCadastroBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadastroBinding.inflate(inflater, container, false)

        binding.proximo1.setOnClickListener {
            checkInputs()
        }

        return binding.root

    }

    private fun checkInputs() {
        val nome = binding.nomePaciente.text.toString()
        val telefone = binding.telefone.text.toString()
        val endereco = binding.endereco.text.toString()
        val genero = binding.genero.text.toString()

        if (nome.isBlank()) {
            binding.nomePaciente.error = "Preencha seu nome."
        } else if (telefone.isBlank() || telefone.length < 11) {
            binding.telefone.error = "Preencha um telefone válido."
        } else if (endereco.isBlank()) {
            binding.endereco.error = "Preencha o seu endereço."
        } else if (genero.isBlank()) {
            binding.genero.error = "Selecione o seu gênero."
        } else {
            val bundle = Bundle()
            bundle.putString("Nome", nome)
            bundle.putString("Telefone", telefone)
            bundle.putString("Endereco", endereco)
            bundle.putString("Genero", genero)

            val fragmentDestino = FragmentComorbidade()

            fragmentDestino.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_cadastro, fragmentDestino)
                .addToBackStack(null)
                .commit()
        }
    }
}
