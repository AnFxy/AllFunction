package cn.com.fenrir.allfunction.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import cn.com.fenrir.allfunction.R
import cn.com.fenrir.allfunction.databinding.FragmentViewpagerBinding

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/9
 */
class MyViewPagerAdapter : RecyclerView.Adapter<MyViewPagerAdapter.MyViewHolder>() {
    private var listIv = mutableListOf<Int>()
    class MyViewHolder(val bind : FragmentViewpagerBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val holder = MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_viewpager,parent,false))
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind.ivViewpager.setImageResource(listIv[position])
        holder.bind.tvPager.text = "这是第${position}个ViewPager页面"
    }

    override fun getItemCount(): Int {
        return listIv.size
    }

    fun refreshData(listInt: List<Int>){
        listIv.addAll(listInt)
        notifyDataSetChanged()
    }
}