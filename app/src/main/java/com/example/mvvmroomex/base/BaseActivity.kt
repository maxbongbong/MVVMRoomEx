package com.example.mvvmroomex.base

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.mvvmroomex.util.CommonUtils

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    private var mToast: Toast? = null

    lateinit var viewDataBinding: T
    abstract val layoutResId: Int
    abstract fun initView()
    abstract fun initData()
    abstract fun initAfter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BaseApplication.activityList.add(this)

        viewDataBinding = DataBindingUtil.setContentView(this, layoutResId)

        initView()
        initData()
        initAfter()

        // 권한설정 변경 시 앱 재시작
        if (savedInstanceState != null) {
            BaseApplication.getInstance().doRestart(this)
        }
    }

    /**
     * Toast Message
     */
    open fun showToastMessage(message: String?) {

        mToast?.cancel()
        mToast = Toast.makeText(this, CommonUtils.makeBreakableText(message), Toast.LENGTH_SHORT)
        mToast?.setGravity(Gravity.BOTTOM, 0, CommonUtils.pxFromDp(this, 15f).toInt())
        mToast?.show()
    }

    open fun hideKeyboard() {

        this.let { act ->

            act.currentFocus?.let { view ->

                val imm = act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }
}