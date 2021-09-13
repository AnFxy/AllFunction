package cn.com.fenrir.allfunction.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import cn.com.fenrir.allfunction.databinding.FragmentPersonBinding
import cn.com.fenrir.allfunction.R
import cn.com.fenrir.allfunction.viewmodel.PersonFragmentViewModel

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/17
 */
class PersonFragment :Fragment(){
    lateinit var bind: FragmentPersonBinding
    val viewmodel: PersonFragmentViewModel by lazy { ViewModelProviders.of(this).get(PersonFragmentViewModel::class.java) }

    private val studentPosition = 1
    private val coursePosition = 2
    private val stuCoursePosition = 3
    private lateinit var manager: FragmentManager
    val fragment1 = PersonDataFragment()
    val fragment2 = PersonDataFragment()
    val fragment3 = PersonDataFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_person,container,false)
        bind.rgPerson.setOnCheckedChangeListener {
                radioGroup, checkedID ->
            when(checkedID){
                R.id.rbtn_student -> {
                    bind.position = studentPosition
                    setButtonState(studentPosition)
                }
                R.id.rbtn_course -> {
                    bind.position = coursePosition
                    setButtonState(coursePosition)
                }
                R.id.rbtn_stu_course -> {
                    bind.position = stuCoursePosition
                    setButtonState(stuCoursePosition)
                }
                else ->{
                    //do nothing
                }
            }
        }
        manager = activity?.supportFragmentManager!!
        return bind.root
    }

    override fun onStart() {
        super.onStart()

        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.add(R.id.fl_person, fragment1).add(R.id.fl_person, fragment2).add(R.id.fl_person, fragment3)
        transaction.commit()
        bind.rgPerson.check(R.id.rbtn_student)
    }

    /**根据点击不同的按钮，切换以及隐藏fragment*/
    fun setButtonState(position: Int) {
        val transaction: FragmentTransaction = manager.beginTransaction()
        when(position){
            stuCoursePosition -> {
                transaction.show(fragment1).hide(fragment2).hide(fragment3)
            }
            coursePosition -> {
                transaction.show(fragment2).hide(fragment1).hide(fragment3)
            }
            else -> {
                transaction.show(fragment3).hide(fragment1).hide(fragment2)
            }
        }
        transaction.commit()
    }
}