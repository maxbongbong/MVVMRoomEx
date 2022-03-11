package com.example.mvvmroomex.viewmodel

import com.example.mvvmroomex.base.BaseViewModel

class ViewModel(private val model : DataModel) : BaseViewModel(){
    interface DataModel{
        fun getData()
    }

    class DataModelImpl: DataModel {
        override fun getData() {
            return
        }
    }
}