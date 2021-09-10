package cn.com.fenrir.allfunction.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.com.fenrir.allfunction.model.bean.UserBean
import cn.com.fenrir.allfunction.net.SingleRetrofit
import cn.com.fenrir.allfunction.net.`interface`.LoginService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/17
 */
class HomeFragmentViewModel : ViewModel() {
    val liveData = MutableLiveData<Observable<UserBean>>()

    fun getDataFromServer(userName: String){
        liveData.value = SingleRetrofit.create(LoginService::class.java)
            .getCall(userName,"12345678901")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}