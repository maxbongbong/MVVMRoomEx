package com.example.mvvmroomex.ui.activity

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmroomex.R
import com.example.mvvmroomex.base.BaseActivity
import com.example.mvvmroomex.databinding.ActivityMainBinding
import com.example.mvvmroomex.model.User
import com.example.mvvmroomex.ui.adapter.UserAdapter
import com.example.mvvmroomex.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.text.Editable
import android.text.TextWatcher
import com.example.mvvmroomex.ui.dialog.PopupChange
import kotlinx.android.synthetic.main.activity_main.etAge
import kotlinx.android.synthetic.main.activity_main.etName

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResId: Int = R.layout.activity_main
    private lateinit var userAdapter: UserAdapter
    private val userData: ArrayList<User> = ArrayList()
    private val userViewModel: UserViewModel by viewModels()
    private var popupChange: PopupChange? = null

    //    lateinit var userViewModel: UserViewModel
    var num = 0

    override fun initView() {

//        userViewModel = ViewModelProvider(
//            this,
//            UserViewModel.Factory(application)
//        )[UserViewModel::class.java]

        viewDataBinding.userViewModel = userViewModel
        viewDataBinding.lifecycleOwner = this
    }

    override fun initData() {
        userViewModel.readAllUser.observe(this, {
            userData.clear()
            userData.addAll(it)

            when (num) {
                1 -> {
                    showToastMessage("추가되었습니다.")
                    etAge.setText("")
                    etName.setText("")
                }

                2 -> showToastMessage("삭제되었습니다.")

                3 -> showToastMessage("수정되었습니다.")

                else -> showToastMessage("데이터가 변경 되었습니다.")
            }
            hideKeyboard()
            num = 0
        })
    }

    override fun initAfter() {
        setOnClickListener()
        initRecyclerView()
        setEditText()
    }

    private fun initRecyclerView() {

        this.let {
            userAdapter = UserAdapter(this, userData)
            rvUser.layoutManager =
                LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
            rvUser.adapter = userAdapter
            userAdapter.selectItem = object : UserAdapter.SelectItem {
                override fun selectItem(position: Int, type: String) {
                    if (userData.size > position) {
                        when (type) {
                            "delete" -> {
                                userViewModel.deleteUser(userData[position])
                                num = 2
                            }

                            "revise" -> {
                                val name = userData[position].name
                                val age = userData[position].age.toString()
                                editItem(name, age, position)
                            }
                        }
                    }
                }
            }
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
        btnAddUser.isEnabled =
            etName.text.toString().isNotEmpty() && etAge.text.toString().isNotEmpty()
    }

    private fun setOnClickListener() {
        btnAddUser.setOnClickListener {
            userViewModel.addUser(User(0, etName.text.toString(), etAge.text.toString().toInt()))
            num = 1
        }
    }

    //AlertDialog 를 사용해서 데이터를 수정한다.
    private fun editItem(name: String, age: String, position: Int) {
        this.let {

            popupChange?.dismiss()
            popupChange = PopupChange(name, age) { menu ->
                when(menu) {
                    0 -> {
                        val id = userData[position].id
                        userViewModel.updateUser(
                            User(
                                id,
                                popupChange?.etName?.text.toString(),
                                popupChange?.etAge?.text.toString().toInt()
                            )
                        )
                        num = 3
                        popupChange?.dismiss()
                    }
                }
            }

            popupChange?.show(supportFragmentManager, null)
        }
    }
}