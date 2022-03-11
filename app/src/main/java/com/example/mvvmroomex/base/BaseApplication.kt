package com.example.mvvmroomex.base

import android.app.Application
import android.content.Context
import android.content.Intent
import com.example.mvvmroomex.ui.activity.MainActivity
import com.example.mvvmroomex.viewmodel.UserViewModel
import com.example.mvvmroomex.viewmodel.ViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import java.util.ArrayList
import kotlin.system.exitProcess

class BaseApplication : Application() {

    companion object{

        var activityList: ArrayList<BaseActivity<*, *>> = ArrayList()

        @Volatile private var instance: BaseApplication? = null
        @JvmStatic fun getInstance(): BaseApplication =
            instance ?: synchronized(this) {
                instance ?: BaseApplication().also {
                    instance = it
                }
            }
    }

    private val appModule = module {
        factory<ViewModel> { ViewModel.DataModelImpl() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(appModule)
        }
    }

    /**
     * 앱 재실행(모든 액티비티 종료 후 재실행)
     * @param context 컨텍스트
     */
    fun doRestart(context: Context?) {

        try {

            for (activity in activityList){
                activity.finish()
            }

        } catch (e: Exception) {

            e.printStackTrace()

        } finally {

            try {

                context?.let {

                    val intent = Intent()
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.setClass(context, MainActivity::class.java)
                    context.startActivity(intent)
                    (context as BaseActivity<*, *>).overridePendingTransition(0, 0)
                }

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 앱종료
     */
    fun finish() {
        try {

            for (activity in activityList){

                activity.finish()
            }
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(0)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}