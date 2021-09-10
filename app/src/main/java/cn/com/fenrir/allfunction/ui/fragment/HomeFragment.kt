package cn.com.fenrir.allfunction.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import cn.com.fenrir.allfunction.R
import cn.com.fenrir.allfunction.databinding.FragmentHomeBinding
import cn.com.fenrir.allfunction.util.RamdomUidUtil
import cn.com.fenrir.allfunction.viewmodel.HomeFragmentViewModel
import com.scwang.smart.refresh.header.ClassicsHeader
import kotlin.random.Random

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/17
 */
class HomeFragment : Fragment(){

    lateinit var bind: FragmentHomeBinding
    val viewModel by lazy { ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_home,container,false)
        viewModel.liveData.observe(viewLifecycleOwner, {
            it.subscribe({/**当服务器返回到数据时，更新UI，同时关闭刷新动画*/
               bind.userFormation = it
               bind.refreshLayout.finishRefresh()
            },{
                it.printStackTrace()
                bind.refreshLayout.finishRefresh(false)
            })
        })

        bind.refreshLayout.setOnRefreshListener { /**关于下拉刷新*/
            getData()/**下拉时进行网络请求API数据*/
        }
        return bind.root
    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    fun getData(){
        viewModel.getDataFromServer(RamdomUidUtil.getUid())
    }
}