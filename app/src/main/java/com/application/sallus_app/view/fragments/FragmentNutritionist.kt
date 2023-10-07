package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.databinding.FragmentHomeNutricionistaBinding
import com.application.sallus_app.databinding.FragmentYoursPatientsBinding

class FragmentNutritionist : Fragment() {
    private lateinit var binding: FragmentHomeNutricionistaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeNutricionistaBinding.inflate(inflater, container, false)


        return binding.root
    }
}