package com.example.data.repository

import com.example.data.storage.sharedprefs.ELiveStorageSharPref
import com.example.domain.models.SessionResultModel
import com.example.domain.repository.ELiveRepository

class ELiveRepositoryImpl(
//    private val restRequest: RestRequest, //TODO тут будет firebase
    private val sharPref: ELiveStorageSharPref
    ): ELiveRepository {
    override fun saveSession(sessionResultModel: SessionResultModel) = sharPref.saveSession(sessionResultModel)
    override fun getSession(): SessionResultModel? = sharPref.getSession()
}