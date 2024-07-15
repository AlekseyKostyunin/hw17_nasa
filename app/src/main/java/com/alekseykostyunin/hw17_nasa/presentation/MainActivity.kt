package com.alekseykostyunin.hw17_nasa.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alekseykostyunin.hw17_nasa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}