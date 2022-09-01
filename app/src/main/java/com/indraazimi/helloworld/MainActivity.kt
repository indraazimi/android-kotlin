package com.indraazimi.helloworld

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.indraazimi.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val divider = DividerItemDecoration(this, RecyclerView.VERTICAL)
        binding.recylerView.addItemDecoration(divider)

        val adapter = MainAdapter()
        binding.recylerView.adapter = adapter
        binding.recylerView.setHasFixedSize(true)

        viewModel.getData().observe(this) {
            adapter.updateData(it)
        }
        viewModel.getStatus().observe(this) {
            updateUI(it)
        }
    }

    private fun updateUI(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.recylerView.visibility = View.GONE
                binding.errorTextView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.recylerView.visibility = View.VISIBLE
                binding.errorTextView.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.recylerView.visibility = View.GONE
                binding.errorTextView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}