package com.example.mvvmroomex.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

//open class BaseViewModel : ViewModel(){
//
//    private val compositeDisposable = CompositeDisposable()
//
//    fun addDisposable(disposable: Disposable) {
//        compositeDisposable.add(disposable)
//    }
//
//    override fun onCleared() {
//        compositeDisposable.clear()
//        super.onCleared()
//    }
//}

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

}