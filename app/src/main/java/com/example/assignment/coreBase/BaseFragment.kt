package com.example.assignment.coreBase

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.assignment.R
import com.example.assignment.dialogs.SuccessDialog

abstract class BaseFragment<Binding : ViewDataBinding, V : ViewModel> : Fragment() {

    private var mViewModel: V? = null

    lateinit var binding: Binding

    abstract fun getViewModels(): V

    abstract fun getBindingVariable(): Int

    lateinit var mBaseActivity: BaseActivity<ViewDataBinding, ViewModel>

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (::binding.isInitialized.not()) {
            binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireActivity() is BaseActivity<*, *>)
            mBaseActivity = requireActivity() as BaseActivity<ViewDataBinding, ViewModel>
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = getViewModels()
        binding.setVariable(getBindingVariable(), mViewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

    }


    /**
     * This function will be used in future if we want to handle specific codes
     * and update UI accordingly
     */
    open fun onDismiss(params: Any?) {
    }


    fun showSuccessDialog(
        code: String,
        message: String, icon: Int = R.drawable.ic_circle_green_updated,
        res: Any? = null

    ) {
        val successErrorDialog = SuccessDialog(
            requireContext(),
            code,
            message, icon, res
        ) {
            onDismiss(it)
        }
        successErrorDialog.show()
    }


    /**
     * This function is used to close keyboard
     */
    fun closeKeyboard() {
        val inputManager =
            requireActivity().applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (requireActivity().currentFocus != null) inputManager.hideSoftInputFromWindow(
            requireActivity().currentFocus!!.windowToken,
            InputMethodManager.RESULT_UNCHANGED_SHOWN
        )
    }
}