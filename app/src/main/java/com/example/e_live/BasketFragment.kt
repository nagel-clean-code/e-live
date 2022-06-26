package com.example.e_live

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_live.presentation.contract.CustomAction
import com.example.e_live.presentation.contract.HasCustomActionToolbar
import com.example.e_live.presentation.viewmodels.BasketViewModel

class BasketFragment : Fragment(), HasCustomActionToolbar {

    companion object {
        fun newInstance() = BasketFragment()
    }

    private lateinit var viewModel: BasketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.basket_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BasketViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun getCustomAction(): CustomAction {
        return CustomAction(0, "Корзина")
    }

}