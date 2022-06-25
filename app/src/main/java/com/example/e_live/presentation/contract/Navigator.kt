package com.example.e_live.presentation.contract

import androidx.fragment.app.Fragment

typealias ResultListener<T> = (T) -> Unit

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {
    fun showFragmentLogin()
    fun showFragmentHome()
    fun showFragmentRegistration()
    fun visibleNavigationMenu(flag: Boolean)
    fun goBack()

}