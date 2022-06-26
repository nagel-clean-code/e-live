package com.example.e_live

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_live.databinding.FragmentWelcomeBinding
import com.example.e_live.databinding.HomeFragmentBinding
import com.example.e_live.presentation.adapters.HomePagerAdapter
import com.example.e_live.presentation.viewmodels.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentBinding

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        settingPagerAdapter()
    }

    private fun settingPagerAdapter(){
        binding.pagerPlaceholder.adapter = activity?.let { HomePagerAdapter(it) }

    }
}