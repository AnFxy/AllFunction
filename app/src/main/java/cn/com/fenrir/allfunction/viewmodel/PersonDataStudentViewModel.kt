package cn.com.fenrir.allfunction.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.com.fenrir.allfunction.model.bean.UserBean
import cn.com.fenrir.allfunction.model.database.table.UserTable
import cn.com.fenrir.allfunction.net.SingleRetrofit
import cn.com.fenrir.allfunction.net.`interface`.LoginService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/9/13
 */
class PersonDataStudentViewModel : ViewModel(){

    val liveData = MutableLiveData<Observable<List<UserTable>>>()

    fun getDataFromLocalDatabase(str: String){
        if (str != ""){

        }else{

        }
    }

}