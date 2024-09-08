package com.demo.customviews

import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.demo.customviews.databinding.ActivityCustomButtonBinding
import com.demo.customviews.databinding.ActivityCustomViewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CustomButtonActivity : AppCompatActivity() {

    private var _binding : ActivityCustomButtonBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCustomButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCustomButton()
        binding.btnCustom.setOnClickListener {
            binding.btnCustom.setBackgroundResource(R.drawable.btn_empty_stroke)
            binding.btnCustom.showLoader()
            lifecycleScope.launch {
                delay(5000)
                binding.btnCustom.setText("SUCCESS")
                binding.btnCustom.setBackgroundResource(R.drawable.btn_enable_bg)
                binding.btnCustom.hideLoader()
            }
        }
    }

    private fun setCustomButton(){
        binding.btnCustom.setText("Continue")
        binding.btnCustom.setBackgroundResource(R.drawable.btn_empty_stroke)
        binding.btnCustom.hideLoader()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}