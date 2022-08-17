package com.example.assignment.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import com.example.assignment.R
import com.example.assignment.databinding.DialogJobProgressBinding

class MyProgressDialog(context: Context) : Dialog(context) {
    var binding: DialogJobProgressBinding? = null

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        binding = DialogJobProgressBinding.inflate(LayoutInflater.from(getContext()))
        setContentView(R.layout.dialog_job_progress)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding?.animationView?.playAnimation()
    }

    override fun dismiss() {
        super.dismiss()
        binding?.animationView?.cancelAnimation()
    }

}