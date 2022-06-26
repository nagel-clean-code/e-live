package com.example.e_live.presentation.contract

/** Имплементируйте интерфейс, если ваш фрагмент переопределяет иконку в тулбаре */

interface  HasCustomActionToolbar {
    fun getCustomAction(): CustomAction
}

class CustomAction(
    val typeIcon: Int,
    val textToolbar: String
)