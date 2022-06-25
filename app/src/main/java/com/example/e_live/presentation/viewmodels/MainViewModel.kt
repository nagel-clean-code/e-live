package com.example.e_live.presentation.viewmodels

import com.example.data.storage.models.SessionResultModelImpl
import com.example.domain.models.SessionResultModel
import com.example.domain.usecases.sharedprefs.GetSessionSharPrefUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSessionSharPrefUseCase: GetSessionSharPrefUseCase
) : BaseViewModel() {

    fun getSession(): SessionResultModelImpl? {
        val resultModel: SessionResultModel? = getSessionSharPrefUseCase.execute()
        return if (resultModel != null)
            resultModel as SessionResultModelImpl
        else
            null
    }

}