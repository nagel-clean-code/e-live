package com.example.data.storage.sharedprefs

import android.content.Context
import com.example.domain.models.SessionResultModel
import com.google.gson.Gson

class SharedPrefELiveStorageImpl(context: Context) : ELiveStorageSharPref {
    private val gson = Gson()

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_MAIN, Context.MODE_PRIVATE)

    override fun saveSession(sessionResultModelImpl: SessionResultModel) {
        val json = gson.toJson(sessionResultModelImpl)
        sharedPreferences.edit().putString(SESSION, json).apply()
    }

    override fun getSession(): SessionResultModel? {
        val json = sharedPreferences.getString(SESSION, "") ?: ""
        if(json.isBlank()){
            return null
        }
        return gson.fromJson(json, SessionResultModel::class.java)
    }

    private companion object {
        const val SHARED_PREFS_MAIN = "SHARED_PREFS_MAIN"
        const val SESSION = "SESSION"
    }
}