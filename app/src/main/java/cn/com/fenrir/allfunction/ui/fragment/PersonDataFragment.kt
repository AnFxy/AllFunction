package cn.com.fenrir.allfunction.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import cn.com.fenrir.allfunction.R
import cn.com.fenrir.allfunction.databinding.FragmentPersonDataBinding
import cn.com.fenrir.allfunction.util.StringUtils
import cn.com.fenrir.allfunction.viewmodel.HomeFragmentViewModel
import cn.com.fenrir.allfunction.viewmodel.PersonDataStudentViewModel

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/9/13
 */
class PersonDataFragment : Fragment(){
    lateinit var bind: FragmentPersonDataBinding

    val viewmodel: PersonDataStudentViewModel by lazy { ViewModelProviders.of(this).get(PersonDataStudentViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_person_data, container, false)

        //点击查询时
        bind.btnSelect.setOnClickListener {
            doSelect()
        }
        //点击添加时
        bind.btnAdd.setOnClickListener {
            doAdd()
        }
        //点击删除时
        bind.btnDelete.setOnClickListener {
            doDelete()
        }

        return bind.root
    }

    fun doSelect(){
        val strSelect = bind.editOne.text.toString()

    }

    fun doAdd(){

    }

    fun doDelete(){

    }
}