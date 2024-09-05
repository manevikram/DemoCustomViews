package com.demo.customviews

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.demo.customviews.databinding.ActivityCustomViewBinding

class CustomViewActivity : AppCompatActivity() {

    private var _binding : ActivityCustomViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCustomViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showCustomView()
    }

    private fun showCustomView() {
        // Click for parent view
        binding.customView.visibility = View.VISIBLE
        binding.customView.setOnClickListener {
            Toast.makeText(this, "click Parent View", Toast.LENGTH_SHORT).show()
        }
        // Click for button on custom view
        binding.customView.onClick(object : CustomViewCallback {
            override fun onClick(value: String) {
                Toast.makeText(this@CustomViewActivity, value, Toast.LENGTH_SHORT).show()
            }
        })
        binding.customView.setText("Testing")
    }
}