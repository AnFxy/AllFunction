package cn.com.fenrir.allfunction.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import cn.com.fenrir.allfunction.R

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/13
 */
object DialogUtils {

    fun showLoginDialog(context: Context){
        val dialog = Dialog(context)
        dialog.setContentView(LayoutInflater.from(context).inflate(R.layout.animation_login,null))
        dialog.setCancelable(false)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.show()
    }
}