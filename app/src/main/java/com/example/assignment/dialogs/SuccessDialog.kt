package com.example.assignment.dialogs

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import com.example.assignment.R
import com.example.assignment.databinding.DialogSuccessErrorLayoutBinding
import com.example.assignment.extensions.setOnSingleClickListener


class SuccessDialog(
    context: Context,
    title: String,
    desc: String, icon: Int = R.drawable.ic_circle_green_updated,
    response: Any? = null,
    val block: (res: Any) -> Unit
) :
    Dialog(context, R.style.SuccessDialog) {
    var binding: DialogSuccessErrorLayoutBinding? = null

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        binding = DialogSuccessErrorLayoutBinding.inflate(LayoutInflater.from(getContext()))
        setContentView(binding!!.root)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window!!.attributes)
        lp.width = ((context.getResources().getDisplayMetrics().widthPixels * 0.90).toInt());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.y = 100
        window!!.attributes = lp
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        window!!.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        )
        window!!.attributes.gravity = Gravity.BOTTOM
        binding?.phoneImage?.setImageResource(icon)
        binding?.lblSuccessTitle?.text = title
        binding?.lblSuccessDesc?.text = desc
        binding?.btnOk?.setOnSingleClickListener {
            block.invoke(response?:"")
            dismiss()
        }
        binding?.closeBtn?.setOnSingleClickListener {
            dismiss()
        }
    }
}