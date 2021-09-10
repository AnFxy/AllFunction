package cn.com.fenrir.allfunction.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import cn.com.fenrir.allfunction.R
import cn.com.fenrir.allfunction.databinding.FragmentPersonBinding
import cn.com.fenrir.allfunction.model.database.MySqliteDatabase
import cn.com.fenrir.allfunction.model.database.table.UserTable
import cn.com.fenrir.allfunction.util.ContextAll
import cn.com.fenrir.allfunction.viewmodel.PersonFragmentViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/17
 */
class PersonFragment :Fragment(){
    lateinit var bind: FragmentPersonBinding
    val viewmodel: PersonFragmentViewModel by lazy { ViewModelProviders.of(this).get(PersonFragmentViewModel::class.java) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_person,container,false)

        viewmodel.liveData.observe(viewLifecycleOwner,{
            it.subscribe ({
                var str = ""
                for (i in it) {
                    str = str + i
                }
                bind.tvPerson.text = str
            },{
                it.printStackTrace()
            })
        })
        bind.btnPerson.setOnClickListener {
            selectData()
        }
        return bind.root
    }

    override fun onStart() {
        super.onStart()
        viewmodel.insertData(UserTable(5,"gui","gui","gui","gui",true,28))
    }
    fun selectData(){
        viewmodel.getDataFromDatabase(ContextAll.getContext())
    }
}