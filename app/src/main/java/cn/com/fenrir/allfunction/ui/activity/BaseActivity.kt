package cn.com.fenrir.allfunction.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity

/**
 * 课题号（GORASA-XXXX)
 * 用于一些基本活动的创建，让其具有基础的功能，如返回，跳转等
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/6
 */
open class BaseActivity : AppCompatActivity(){
    var listActivity = mutableListOf<BaseActivity>()
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        listActivity.add(this)
    }

    fun closeApp(){
        for (i in listActivity){
            i.finish()
        }
        listActivity.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        listActivity.remove(this)
    }

    fun <T: BaseActivity>createIntent(cls: Class<T>){
        val intent = Intent().setClass(this,cls)
        this.startActivity(intent)
    }
}