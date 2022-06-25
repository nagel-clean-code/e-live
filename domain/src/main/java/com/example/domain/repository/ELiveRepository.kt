package com.example.domain.repository

import com.example.domain.models.SessionResultModel

interface ELiveRepository {
    fun saveSession(sessionResultModel: SessionResultModel)
    fun getSession(): SessionResultModel?
}