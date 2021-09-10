package cn.com.fenrir.allfunction.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cn.com.fenrir.allfunction.R
import cn.com.fenrir.allfunction.databinding.FragmentSettingBinding

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/17
 */
class SettingFragment  : Fragment(){
    lateinit var bind: FragmentSettingBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_setting,container,false)
        return bind.root
    }
}