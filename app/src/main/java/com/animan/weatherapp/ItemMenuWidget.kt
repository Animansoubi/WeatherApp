package com.animan.weatherapp

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CompoundButton
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.animan.weatherapp.databinding.ItemMenuWidgetBinding


class ItemMenuWidget : FrameLayout {

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private var binding = ItemMenuWidgetBinding.inflate(LayoutInflater.from(context), this, true)

    private fun init(attrs: AttributeSet?) {
        if (attrs == null) {
            binding.tvItemMenuWidget.text = ""
            binding.swTemMenuWidget.isVisible = false
            return
        }

        val attrs = context.obtainStyledAttributes(attrs, R.styleable.ItemMenuWidget, 0, 0)

        val text = attrs.getString(R.styleable.ItemMenuWidget_text)
        val drawable = attrs.getDrawable(R.styleable.ItemMenuWidget_img_icon)
        val showSwitch = attrs.getBoolean(R.styleable.ItemMenuWidget_switch_visibility, false)

        binding.tvItemMenuWidget.text = text
        binding.imgItemMenuWidget.background = drawable
        binding.swTemMenuWidget.isVisible = showSwitch

        attrs.recycle()
    }

    fun setOnSwitchChangedListener(onChangedSwitch: (button: CompoundButton, state: Boolean) -> Unit) {
        binding.swTemMenuWidget.setOnCheckedChangeListener { compoundButton, b ->
            onChangedSwitch(compoundButton, b)
        }
    }
}