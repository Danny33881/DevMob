package com.example.lab2

import android.graphics.Color
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

class Click: ClickableSpan() {
    override fun onClick(widget: View) {
        TODO("Not yet implemented")
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)

        ds.isUnderlineText = false
        ds.color = Color.parseColor("#4B09F3")
    }
}