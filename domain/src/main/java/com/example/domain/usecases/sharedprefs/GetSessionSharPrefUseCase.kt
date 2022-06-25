package com.example.domain.usecases.sharedprefs

import com.example.domain.models.SessionResultModel
import com.example.domain.repository.ELiveRepository


class GetSessionSharPrefUseCase(private val repository: ELiveRepository) {
    fun execute(): SessionResultModel? = repository.getSession()
}