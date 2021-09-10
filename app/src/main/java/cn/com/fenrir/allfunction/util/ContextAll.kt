package cn.com.fenrir.allfunction.util


import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/9
 */
class ContextAll : Application(){
    companion object{
        var  _context:Application? = null

        fun getContext():Context{
            return _context!!
        }

        fun getVersionCode(): Long{
            var versionCode: Long = 0
            try {
                val packageInfo = getContext().packageManager.getPackageInfo(getContext().packageName,0)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                    versionCode = packageInfo.longVersionCode
                }else{
                    versionCode = packageInfo.versionCode.toLong()
                }
                return versionCode
            } catch (throwable: Throwable) {
                Log.e("VersionCode","message : ${throwable.message} \ntoString() : " +
                        "${throwable.toString()}\nprintStackTrace() 输出堆栈信息：" +
                        "\n${throwable.printStackTrace()}")
                return 0
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        _context = this
    }


}