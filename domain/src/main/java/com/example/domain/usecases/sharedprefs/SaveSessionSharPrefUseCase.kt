package com.example.domain.usecases.sharedprefs

import com.example.domain.models.SessionResultModel
import com.example.domain.repository.ELiveRepository

class SaveSessionSharPrefUseCase(private val repository: ELiveRepository) {
    fun execute(sessionResultModel: SessionResultModel) = repository.saveSession(sessionResultModel)
}