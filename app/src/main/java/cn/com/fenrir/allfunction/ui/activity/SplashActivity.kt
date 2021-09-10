package cn.com.fenrir.allfunction.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import cn.com.fenrir.allfunction.R
import cn.com.fenrir.allfunction.util.Constants
import cn.com.fenrir.allfunction.util.ContextAll
import cn.com.fenrir.allfunction.util.PrefUtil
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {
    lateinit var dipose: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        /**延时1.5秒跳转到主活动*/
       dipose = Observable.just(1)
            .delay(1500,TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                /**检测是否是第一次安装**/
                checkIsFristInstall()
            }
    }

    fun checkIsFristInstall(){
        val oldVersionCode = PrefUtil.readLong(Constants.OLD_VERSION_CODE,0L)
        val newVersionCode = ContextAll.getVersionCode()

        if (oldVersionCode!= newVersionCode){/**第一次安装*/
            createIntent(GuideActivity::class.java)
        }else{
            createIntent(LoginActivity::class.java)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dipose.dispose()
    }
}