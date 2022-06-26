package com.example.e_live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_live.databinding.FragmentStoreBinding
import com.example.e_live.presentation.contract.CustomAction
import com.example.e_live.presentation.contract.HasCustomActionToolbar
import com.example.e_live.presentation.contract.navigator

class StoreFragment : Fragment(), HasCustomActionToolbar {
    private lateinit var binding: FragmentStoreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.button.setOnClickListener {
            navigator().showFragmentShowTree()
        }
        binding.button2.setOnClickListener {
            navigator().showFragmentShowTree()
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentStoreBinding.inflate(layoutInflater)
    }

    override fun getCustomAction(): CustomAction {
        return CustomAction(Constants.TYPE_ICON_DEFAULT, "Каталог")
    }
}