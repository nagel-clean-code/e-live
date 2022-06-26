package com.example.e_live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_live.presentation.contract.CustomAction
import com.example.e_live.presentation.contract.HasCustomActionToolbar

class StoreFragment : Fragment(), HasCustomActionToolbar {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun getCustomAction(): CustomAction {
        return CustomAction(Constants.TYPE_ICON_DEFAULT, "Каталог")
    }
}