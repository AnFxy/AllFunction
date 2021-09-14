package cn.com.fenrir.allfunction.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import cn.com.fenrir.allfunction.R
import cn.com.fenrir.allfunction.databinding.ItemSelectUserBinding

import cn.com.fenrir.allfunction.model.database.table.UserTable

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/9/14
 */
class UserRecycleAdapter : RecyclerView.Adapter<UserRecycleAdapter.MyViewHolder>() {

    private var TIP_FALG = true;
    private var listData: MutableList<UserTable> = mutableListOf();

    class MyViewHolder(val bind: ItemSelectUserBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binder = DataBindingUtil.inflate<ItemSelectUserBinding>(LayoutInflater.from(parent.context), R.layout.item_select_user, parent, false)
        val holder = MyViewHolder(binder)

        binder.ivShowTip.setOnClickListener {
            if (TIP_FALG){
                binder.ivShowTip.setImageResource(R.drawable.icon_triangle)
                binder.llUserDetail.visibility = View.VISIBLE
                TIP_FALG = false
            }else{
                binder.ivShowTip.setImageResource(R.drawable.icon_triangle_down)
                binder.llUserDetail.visibility = View.GONE
                TIP_FALG = true
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind.useSelectData = listData.get(position)
    }

    override fun getItemCount() = listData.size

    fun refreshListData(newListData: List<UserTable>){
        listData.clear()
        if (newListData.size != 0){
            listData.addAll(newListData)
        }
        notifyDataSetChanged()
    }
}