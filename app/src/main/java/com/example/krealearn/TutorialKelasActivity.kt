package com.example.krealearn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.krealearn.databinding.ActivityTutorialKelasBinding

class TutorialKelasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialKelasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialKelasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )
        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialKelasViewPager.adapter = adapter
        binding.dotIndicator.attachTo(binding.tutorialKelasViewPager)
    }
}