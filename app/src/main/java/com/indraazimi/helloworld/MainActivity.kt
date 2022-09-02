package com.indraazimi.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.indraazimi.helloworld.database.DiaryDb
import com.indraazimi.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        val db = DiaryDb.getInstance(this)
        val factory = MainViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        viewModel.data.observe(this) {
            Log.d("DATA", "Jumlah data: ${it.size}")
        }
    }
}