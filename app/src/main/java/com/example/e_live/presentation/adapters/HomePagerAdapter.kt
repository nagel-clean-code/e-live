package com.example.e_live.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.e_live.Constants
import com.example.e_live.WelcomeItemFragment

class HomePagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount() = Constants.COUNT_FRAGMENT_VIEW

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> WelcomeItemFragment("Раз ты здесь, значит тебе точно не безразлична природа и ты хочешь узнать, что же тут происходит","Листай вправо")
            1 -> WelcomeItemFragment("Здесь ты можешь  инвестировать в деревья и получать доход с их продажи и аренды", "Листай вправо")
            2 -> WelcomeItemFragment("А также ты можешь взять любое дерево из каталога в аренду\n" +
                    "(что актуально на новый год)\n" +
                    "А еще у нас есть милая елка тамагочи", "Начнём?")
            else -> throw IllegalArgumentException("Нет такой вкладки")
        }
    }


}