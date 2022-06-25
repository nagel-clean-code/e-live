package com.example.data.storage.sharedprefs

import com.example.domain.models.SessionResultModel

interface ELiveStorageSharPref {
    fun saveSession(sessionResultModelImpl: SessionResultModel)
    fun getSession(): SessionResultModel?
}