package cn.com.fenrir.allfunction.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.com.fenrir.allfunction.model.database.MySqliteDatabase
import cn.com.fenrir.allfunction.model.database.table.UserTable
import cn.com.fenrir.allfunction.util.ContextAll
import cn.com.fenrir.allfunction.util.RxThreadUtils
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/17
 */
class PersonFragmentViewModel : ViewModel(){

    val liveData = MutableLiveData<Flowable<List<String>>>()
    val dao = MySqliteDatabase.getDatabase(ContextAll.getContext()).useDao()

    fun getDataFromDatabase(context: Context){
      liveData.value  =  dao
            .getAllDataFromUserTable()
            .flatMap {
                val list = mutableListOf<String>()
                for (i in it) {
                    list.add(i.fakeName)
                }
                Flowable.fromArray(list)
            }
          .compose(RxThreadUtils.flowableToMain())
    }

    fun insertData(userTable: UserTable){
        dao.insert(userTable)
            .compose(RxThreadUtils.completeToMain())
            .subscribe()
    }
}