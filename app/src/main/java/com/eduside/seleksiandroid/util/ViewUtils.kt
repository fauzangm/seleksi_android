package com.eduside.seleksiandroid.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import com.eduside.seleksiandroid.R
import com.google.android.material.snackbar.Snackbar
import splitties.alertdialog.appcompat.*
import splitties.views.textColorResource

fun showError(context: Context, message: String) {
    context.alertDialog {
        setMessage(message)
        setCancelable(false)
        okButton { }
    }.onShow {
        positiveButton.textColorResource = R.color.colorBlack
    }.show()
}

fun showLoading(context: Activity, pb: ProgressBar, isLoading: Boolean) {
    if (isLoading) {
        pb.visibility = View.VISIBLE
        pb.bringToFront()
        context.window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    } else {
        pb.visibility = View.INVISIBLE
        context.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}

fun showLoading(context: Activity, sb: Snackbar, isLoading: Boolean) {
    if (isLoading) {
        if(!sb.isShownOrQueued) sb.show()
        context.window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    } else {
        if(sb.isShown) sb.dismiss()
        context.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}