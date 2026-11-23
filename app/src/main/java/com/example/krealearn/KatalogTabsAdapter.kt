package com.example.krealearn

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class KatalogTabsAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {
    // Jumlah total tab yang ada
    override fun getItemCount(): Int = 3

    // Menentukan Fragment yang ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabSedangFragment()
            1 -> TabSelesaiFragment()
            2 -> TabKatalogFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}