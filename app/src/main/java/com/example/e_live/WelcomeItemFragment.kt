package com.example.e_live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_live.databinding.FragmentWelcomItemBinding

class WelcomeItemFragment(val text: String, val textButton: String) : Fragment() {
    private lateinit var binding: FragmentWelcomItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentWelcomItemBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.text.text = text
        binding.button.text = textButton
        return binding.root
    }

}