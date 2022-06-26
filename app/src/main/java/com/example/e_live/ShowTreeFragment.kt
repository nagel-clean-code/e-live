package com.example.e_live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.storage.models.TreeModel
import com.example.e_live.databinding.ShowTreeFragmentBinding
import com.example.e_live.presentation.adapters.SearchResultAdapter
import com.example.e_live.presentation.contract.navigator
import com.example.e_live.presentation.viewmodels.ShowTreeViewModel

class ShowTreeFragment : Fragment() {
    private var searchAdapter: SearchResultAdapter? = null
    private lateinit var binding: ShowTreeFragmentBinding

    private val viewModel: ShowTreeViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShowTreeFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.displayProduct.observe(viewLifecycleOwner) {
            if (it != null) {
                displayCampaign()
                viewModel.displayProduct.postValue(null)
            }
        }
        return binding.root
    }
    private fun displayCampaign() =
        navigator().showFragmentCard()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val listResp = mutableListOf<TreeModel>()
        for(i in 1..20){
            listResp.add(TreeModel(i,"",1000+i))
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        searchAdapter =
            SearchResultAdapter(listResp, viewModel, requireActivity().applicationContext)
        binding.recyclerView.adapter = searchAdapter

    }

}