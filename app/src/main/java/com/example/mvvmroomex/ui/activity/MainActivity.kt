package com.example.mvvmroomex.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmroomex.R
import com.example.mvvmroomex.base.BaseActivity
import com.example.mvvmroomex.base.BaseViewModel
import com.example.mvvmroomex.databinding.ActivityMainBinding
import com.example.mvvmroomex.model.User
import com.example.mvvmroomex.ui.adapter.UserAdapter
import com.example.mvvmroomex.viewmodel.UserViewModel
import com.example.mvvmroomex.viewmodel.ViewModel
import kotlinx.android.synthetic.main.activity_main.*

//class MainActivity : BaseActivity() {
class MainActivity : BaseActivity<ActivityMainBinding, ViewModel>() {

    override val layoutResId: Int = R.layout.activity_main
//    override val userViewModel: UserViewModel by viewModels()
    override val userViewModel: ViewModel by viewModels()

    private lateinit var userAdapter: UserAdapter
    private val userData: ArrayList<User> = ArrayList()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
//        binding.userViewModel = userViewModel
//        binding.lifecycleOwner = this
//
//        userViewModel.readAllUser.observe(this, {
//            userData.clear()
//            userData.addAll(it)
//            userAdapter.notifyDataSetChanged()
//        })
//
//        setOnClickListener()
//        initRecyclerView()
//    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun initAfter() {
        TODO("Not yet implemented")
    }

    private fun initRecyclerView() {

        this.let {
            userAdapter = UserAdapter(this, userData)
            rvUser.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
            rvUser.adapter = userAdapter
            userAdapter.selectItem = object : UserAdapter.SelectItem {
                override fun selectItem(position: Int, type: String) {
                    if (userData.size > position) {
                        if (type == "delete") {
                            userViewModel.deleteUser(userData[position])
                        }
                    }
                }
            }
        }
    }

    private fun setOnClickListener() {
        btnAddUser.setOnClickListener {
            val id = userData.size
            userViewModel.addUser(User(0, etName.text.toString(), etAge.text.toString().toInt()))
        }
    }
}