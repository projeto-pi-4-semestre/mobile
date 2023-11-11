package com.application.sallus_app.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.application.sallus_app.adapter.FoodAdapter
import com.application.sallus_app.databinding.FragmentFoodsBinding
import com.application.sallus_app.viewmodel.FoodViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentFoods : Fragment() {

    lateinit var binding: FragmentFoodsBinding
    private val viewmodel: FoodViewModel by viewModel()
    lateinit var adapter: FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFoodsBinding.inflate(inflater, container, false)

        setupView()
        setupObservers()

        return binding.root
    }

    private fun setupView() {
        adapter = FoodAdapter()
        binding.recyclerViewFood.adapter = adapter
    }

    private fun setupObservers() {

        val sugestoesAlimentos = mutableListOf<String>()

        val adapterSearchbarFoods = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            sugestoesAlimentos
        )

        viewmodel.buscarTodosAlimentos()

        viewmodel.listAlimentos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewmodel.listAlimentos.observe(viewLifecycleOwner) { alimentos ->
            for (alimento in alimentos) {
                sugestoesAlimentos.add(alimento.nome)
            }
        }

        binding.searchBarFoods.setAdapter(adapterSearchbarFoods)

        binding.searchBarFoods.setOnItemClickListener { parent, _, position, _ ->

            val selectedFood = parent.getItemAtPosition(position) as String
            viewmodel.buscarAlimentoPeloNome(selectedFood)

            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE

            viewmodel.alimentoInformadoSearchbar.observe(viewLifecycleOwner) {
                adapter.submitListOnlyFood(it)
            }
            binding.searchBarFoods.hideKeyboard()
        }

        binding.textviewButtonLimparSelecao.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.GONE
            binding.searchBarFoods.setText("")

            viewmodel.listAlimentos.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.cardviewCarnesVermelhaCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Carne bovina")
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.cardviewCarnesBrancasCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Frango")
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.cardviewFrutasCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Fruta")
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.cardviewGraosCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Grão")
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.cardviewMassasCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Massa")
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.cardviewFrutosMarCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Frutos do mar")
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.cardviewVerdurasCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Verdura")
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.cardviewLegumesCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Legume")
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.cardviewAlimentosProntosCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Alimento pronto")
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

    }

    private fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

}