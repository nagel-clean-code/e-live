package com.example.e_live.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.data.storage.models.TreeModel
import com.example.e_live.presentation.adapters.SearchResultAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowTreeViewModel @Inject constructor(): BaseViewModel(), SearchResultAdapter.ListenerItem {

    val displayProduct = MutableLiveData<TreeModel>()

    override fun onDisplayProduct() {
        displayProduct.postValue(TreeModel(1,"",100))
    }

}