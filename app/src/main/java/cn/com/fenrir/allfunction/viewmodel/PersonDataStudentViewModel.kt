package cn.com.fenrir.allfunction.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.com.fenrir.allfunction.model.bean.UserBean
import cn.com.fenrir.allfunction.model.database.MySqliteDatabase
import cn.com.fenrir.allfunction.model.database.table.UserTable
import cn.com.fenrir.allfunction.net.SingleRetrofit
import cn.com.fenrir.allfunction.net.`interface`.LoginService
import cn.com.fenrir.allfunction.util.ContextAll
import cn.com.fenrir.allfunction.util.RxThreadUtils
import io.reactivex.Completable
import io.reactivex.Flowable
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

    val liveDataSelect = MutableLiveData<Flowable<List<UserTable>>>()
    val liveDataInsert = MutableLiveData<Completable>()
    val liveDataDelete = MutableLiveData<Completable>()
    val userDao = MySqliteDatabase.getDatabase(ContextAll.getContext()).useDao()

    fun selectUser(str: String){
        if (str != ""){
            selectWithCodition(str)
        }else{
            selectAllUser()
        }
    }

    //有条件查询用户信息
    fun selectWithCodition(strName: String){
        liveDataSelect.value = userDao.getSingleData(strName).compose(RxThreadUtils.flowableToMain())
    }

    //查询全部用户的信息
    fun selectAllUser(){
        liveDataSelect.value = userDao.getAllDataFromUserTable().compose(RxThreadUtils.flowableToMain())
    }

    //添加用户信息
    fun addAUser(user: UserTable){
        liveDataInsert.value = userDao.insert(user).compose(RxThreadUtils.completeToMain())
    }

    //删除用户
    fun removeUser(strName: String){
        liveDataDelete.value = userDao.deleteWithName(strName).compose(RxThreadUtils.completeToMain())
    }
}