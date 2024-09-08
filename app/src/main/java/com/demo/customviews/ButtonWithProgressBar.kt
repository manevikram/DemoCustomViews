package com.demo.customviews

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.demo.customviews.databinding.ButtonWithProgressBarBinding

/**
 * Created by Vikram Mane on 9/8/24.
 */
class ButtonWithProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding = ButtonWithProgressBarBinding.inflate(LayoutInflater.from(context), this, true)

    private var text = ""
    private var size = ButtonWithProgressBarSize.DEFAULT
    private var isButtonEnabled = true
    private var backgroundColor = 0
    private var textColor = 0
    private var showingLoader = false

    enum class ButtonWithProgressBarSize {
        DEFAULT, MINI, LARGE, LARGEST
    }

    // Initialization
    init {
        getStuffFromXML(context, attrs)
        initButtonWithProgressBar()
    }

    @SuppressLint("Recycle")
    private fun getStuffFromXML(context: Context, attrs: AttributeSet?){
        val data = context.obtainStyledAttributes(attrs, R.styleable.ButtonWithProgressBar)
        data.let{
            text = it.getString(R.styleable.ButtonWithProgressBar_text).toString()
            isButtonEnabled = it.getBoolean(R.styleable.ButtonWithProgressBar_enabled, true)
            backgroundColor = it.getColor(R.styleable.ButtonWithProgressBar_backgroundColor, context.getColor(R.color.blue))
            textColor = it.getColor(R.styleable.ButtonWithProgressBar_custom_text_color, context.getColor(R.color.white))
            size = when(it.getInt(R.styleable.ButtonWithProgressBar_size, 0)){
                0 -> ButtonWithProgressBarSize.DEFAULT
                1 -> ButtonWithProgressBarSize.MINI
                2 -> ButtonWithProgressBarSize.LARGE
                3 -> ButtonWithProgressBarSize.LARGEST
                else -> {
                    ButtonWithProgressBarSize.DEFAULT
                }
            }
        }
    }

    private fun initButtonWithProgressBar(){
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        if(text.isNotEmpty()){
            binding.tvText.text = text
        }
        minimumWidth = resources.getDimension(R.dimen.button_mini_width).toInt()

        if (ButtonWithProgressBarSize.MINI == size){
            setMeasuredDimension(
                measuredWidth, resources.getDimension(R.dimen.button_mini_height).toInt()
            )
            binding.rlRootLayout.layoutParams.height = resources.getDimension(R.dimen.button_mini_height).toInt()
        } else if (ButtonWithProgressBarSize.LARGE == size){
            setMeasuredDimension(
                measuredWidth, resources.getDimension(R.dimen.button_large_height).toInt()
            )
            binding.rlRootLayout.layoutParams.height = resources.getDimension(R.dimen.button_large_height).toInt()
        } else if (ButtonWithProgressBarSize.LARGEST == size){
            setMeasuredDimension(
                measuredWidth, resources.getDimension(R.dimen.button_largest_height).toInt()
            )
            binding.rlRootLayout.layoutParams.height = resources.getDimension(R.dimen.button_largest_height).toInt()
        } else {
            setMeasuredDimension(
                measuredWidth, resources.getDimension(R.dimen.button_largest_height).toInt()
            )
            binding.rlRootLayout.layoutParams.height = resources.getDimension(R.dimen.button_largest_height).toInt()
        }

        binding.rlRootLayout.setPadding(
            resources.getDimension(R.dimen.margin_10).toInt(),
            0,
            resources.getDimension(R.dimen.margin_10).toInt(),
            0
        )
        refreshDrawableState() // Refresh the drawable state when initialised
    }

    fun setText(text : String){
        binding.tvText.text = text
    }

    fun showLoader(){
        showingLoader = true
        binding.tvText.visibility = View.GONE
        binding.pbLoader.visibility = View.VISIBLE
    }

    fun hideLoader(){
        showingLoader = false
        binding.tvText.visibility = View.VISIBLE
        binding.pbLoader.visibility = View.GONE
    }

}