package com.musicabinet.mobile.ui.view

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import com.musicabinet.mobile.R

/**
 * @author Kirchhoff-
 */
class LoadingDialog : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context!!, R.style.LoadingDialog)
                .setView(View.inflate(activity, R.layout.dialog_loading, null))
                .create();
    }
}