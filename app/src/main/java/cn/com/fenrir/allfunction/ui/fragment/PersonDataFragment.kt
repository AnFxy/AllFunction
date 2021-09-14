package cn.com.fenrir.allfunction.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import cn.com.fenrir.allfunction.R
import cn.com.fenrir.allfunction.databinding.FragmentPersonDataBinding
import cn.com.fenrir.allfunction.model.database.table.UserTable
import cn.com.fenrir.allfunction.ui.adapter.UserRecycleAdapter
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
    private val adapter = UserRecycleAdapter();

    private var TIP_ADD_FLAG = true
    private var TIP_SELECT_FLAG = true

    val viewmodel: PersonDataStudentViewModel by lazy { ViewModelProviders.of(this).get(PersonDataStudentViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_person_data, container, false)

        bind.rvSelect.adapter = adapter

        //查询结果处理
        viewmodel.liveDataSelect.observe(viewLifecycleOwner, {
            it.subscribe {
                if (it.size != 0){
                    bind.rvSelect.visibility = View.VISIBLE
                    bind.ivNoData.visibility = View.GONE
                    bind.ivHideTip.visibility = View.VISIBLE
                    adapter.refreshListData(it)
                }else{
                    bind.rvSelect.visibility = View.GONE
                    bind.ivNoData.visibility = View.VISIBLE
                    bind.ivHideTip.visibility = View.GONE
                }
                bind.ivHideTip.setImageResource(R.drawable.icon_triangle)
                TIP_SELECT_FLAG = false
            }
        })

        //添加结果处理
        viewmodel.liveDataInsert.observe(viewLifecycleOwner, {
            it.subscribe({

            }, {
                it.printStackTrace()//添加失败
            })
        })

        //删除结果处理
        viewmodel.liveDataDelete.observe(viewLifecycleOwner, {
            it.subscribe({

            }, {
                it.printStackTrace()//删除失败
            })
        })

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

        //点击展开添加按钮时
        bind.ivHideAddTip.setOnClickListener {
            if (TIP_ADD_FLAG){
                bind.llAdd.visibility = View.VISIBLE
                bind.ivHideAddTip.setImageResource(R.drawable.icon_triangle)
                TIP_ADD_FLAG = false
            }else{
                bind.llAdd.visibility = View.GONE
                bind.ivHideAddTip.setImageResource(R.drawable.icon_triangle_down)
                TIP_ADD_FLAG = true
            }
        }

        bind.ivHideTip.setOnClickListener {
            if (TIP_SELECT_FLAG){
                bind.rvSelect.visibility = View.VISIBLE
                bind.ivHideTip.setImageResource(R.drawable.icon_triangle)
                TIP_SELECT_FLAG = false
            }else{
                bind.rvSelect.visibility = View.GONE
                bind.ivHideTip.setImageResource(R.drawable.icon_triangle_down)
                TIP_SELECT_FLAG = true
            }
        }
        return bind.root
    }

    fun doSelect(){
        val strSelect = bind.editOne.text.toString()
        viewmodel.selectUser(strSelect)
    }

    fun doAdd(){
        val id = StringUtils.getTextContent(bind.editAddId)
        val count = StringUtils.getTextContent(bind.editAddCount)
        val password = StringUtils.getTextContent(bind.editAddPassword)
        val name = StringUtils.getTextContent(bind.editAddName)
        val fakeName = StringUtils.getTextContent(bind.editAddFakename)
        val sex = StringUtils.getTextContent(bind.editAddSex)
        val age = StringUtils.getTextContent(bind.editAddAge)
        if (id == "" || count == "" || password == "" || name == "" || fakeName == "" || sex == "" || age == ""){
            Toast.makeText(context, "存在数据为空，添加失败！", Toast.LENGTH_SHORT).show()
        }else{
            val userTable = UserTable(id = id.toInt(), userCount = count, passWord = password, userName = name, fakeName = fakeName, sex = if (sex == "true") true else false, age = age.toInt())
            viewmodel.addAUser(userTable)
            //重置为空
            StringUtils.setTextContent(bind.editAddId)
            StringUtils.setTextContent(bind.editAddCount)
            StringUtils.setTextContent(bind.editAddPassword)
            StringUtils.setTextContent(bind.editAddName)
            StringUtils.setTextContent(bind.editAddFakename)
            StringUtils.setTextContent(bind.editAddSex)
            StringUtils.setTextContent(bind.editAddAge)
        }

    }

    fun doDelete(){
        val strName = bind.editDeleteStudent.text.toString()
        if (strName != ""){
            viewmodel.removeUser(strName)
        }
        StringUtils.setTextContent(bind.editDeleteStudent)
    }

}