package com.example.e_live.presentation.viewmodels

import com.example.data.storage.models.SessionResultModelImpl
import com.example.domain.usecases.sharedprefs.SaveSessionSharPrefUseCase
import com.example.e_live.presentation.models.state.SuccessResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val saveSessionSharPrefUseCase: SaveSessionSharPrefUseCase,
) : BaseViewModel() {

    private val _loadResultMutableLiveData = MutableLiveResult<SessionResultModelImpl>()
    val loadResultModelLiveData: LiveResult<SessionResultModelImpl> = _loadResultMutableLiveData


    fun saveSession(sessionResultModelImpl: SessionResultModelImpl) =
        saveSessionSharPrefUseCase.execute(sessionResultModelImpl)

    fun requestLogin(mail: String, password: String) {
        //TODO пока просто заглушка
        _loadResultMutableLiveData.value = SuccessResult(SessionResultModelImpl("student17let@mail.ru"))
    }
}
