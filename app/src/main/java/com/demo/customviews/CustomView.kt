package com.demo.customviews

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import com.demo.customviews.databinding.CustomViewBinding

/**
 * Created by Vikram Mane on 9/4/24.
 */
@SuppressLint("StringFormatInvalid")
class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var callback: CustomViewCallback? = null
    private val binding = CustomViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.withStyledAttributes(attrs, R.styleable.CustomView) {
            val value = getInt(R.styleable.CustomView_custom_value, 0)
            binding.tvValue.text = String.format(context.getString(R.string.txt_value), value)
        }
        binding.tvAdd.setOnClickListener {
            callback?.onClick(binding.tvValue.text.toString())
        }
    }

    fun onClick(listener: CustomViewCallback) {
        callback = listener
    }

    fun setText(text : String){
        binding.tvAdd.text = text
    }
}

interface CustomViewCallback{
    fun onClick(txt:String)
}
