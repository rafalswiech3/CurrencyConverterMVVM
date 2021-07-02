package com.rafal.currencyconverterretrofitmvvm

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.rafal.currencyconverterretrofitmvvm.databinding.ActivityMainBinding
import com.rafal.currencyconverterretrofitmvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: MainViewModel by viewModels()

        binding.spTo.setSelection(1)

        viewModel.getCurrencyRates().observe(this, Observer<String> {
            binding.progressBar.visibility = View.GONE
            binding.tvResult.visibility = View.VISIBLE
            val resultText = it
            binding.tvResult.text = resultText

        })

        binding.btnConvert.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.tvResult.visibility = View.GONE
            viewModel.loadRates(
                binding.spFrom.selectedItem.toString(),
                binding.spTo.selectedItem.toString(),
                binding.inputAmount.text.toString()
            )
        }
    }
}