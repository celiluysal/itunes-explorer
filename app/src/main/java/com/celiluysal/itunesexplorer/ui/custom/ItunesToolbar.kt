package com.celiluysal.itunesexplorer.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.celiluysal.itunesexplorer.R
import com.celiluysal.itunesexplorer.databinding.LayoutItunesToolbarBinding
import com.celiluysal.itunesexplorer.extensions.invisible

class ItunesToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var visibleStartButton: Boolean = true
        set(value) {
            field = value
            if (!visibleStartButton) {
                binding.toolbarStartImageButton.invisible()
            }
        }

    var title: String? = null
        set(value) {
            field = value
            field?.let {
                binding.toolbarTitleTextview.text = field
            }
        }

    private val binding: LayoutItunesToolbarBinding =
        LayoutItunesToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ItunesToolbar)

        visibleStartButton =
            attributes.getBoolean(R.styleable.ItunesToolbar_visibleStartButton, true)
        title = attributes.getString(R.styleable.ItunesToolbar_title)

        attributes.recycle()

        binding.toolbarStartImageButton.setOnClickListener {
            startButtonSetOnClickListener()
        }
    }

    var startButtonSetOnClickListener: () -> Unit = { }

}