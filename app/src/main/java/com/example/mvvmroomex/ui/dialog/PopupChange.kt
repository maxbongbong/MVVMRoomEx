package com.example.mvvmroomex.ui.dialog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.mvvmroomex.R
import kotlinx.android.synthetic.main.dialog_user.*

class PopupChange(
    private val name: String,
    private val age: String,
    val selectMenu: (pos: Int) -> Unit
) : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ThemeOverlay_AppCompat_Dialog)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()

        setEditText()

        etName.setText(name)
        etAge.setText(age)
    }

    private fun setOnClickListener() {
        btnRevise.setOnClickListener {
            selectMenu(0)
        }

        btnCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun setEditText() {
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validate()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validate()
            }

            override fun afterTextChanged(p0: Editable?) {
                validate()
            }
        }

        etName.addTextChangedListener(watcher)
        etAge.addTextChangedListener(watcher)
    }

    private fun validate() {
        btnRevise.isEnabled =
            etName.text.toString().isNotEmpty() && etAge.text.toString().isNotEmpty()
    }
}