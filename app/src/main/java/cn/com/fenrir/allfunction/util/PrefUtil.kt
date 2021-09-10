package cn.com.fenrir.allfunction.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.security.AccessController.getContext

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/9
 */
object PrefUtil {
    private val sp: SharedPreferences.Editor = ContextAll.getContext().getSharedPreferences(Constants.SHAREd_PREFERENCE_NAME,Context.MODE_PRIVATE).edit()
    private val spget = ContextAll.getContext().getSharedPreferences(Constants.SHAREd_PREFERENCE_NAME,Context.MODE_PRIVATE)

    fun writeInt(key: String,value: Int){
        try {
            sp.putInt(key,value)
            sp.apply()
        } catch (throwable: Throwable) {
            Log.e("PrefUtil","message : ${throwable.message} \ntoString() : " +
                    "${throwable.toString()}\nprintStackTrace() 输出堆栈信息：" +
                    "\n${throwable.printStackTrace()}")
        }
    }

    fun writeBoolean(key: String,value: Boolean){
        try {
            sp.putBoolean(key,value)
            sp.apply()
        } catch (throwable: Throwable) {
            Log.e("PrefUtil","message : ${throwable.message} \ntoString() : " +
                    "${throwable.toString()}\nprintStackTrace() 输出堆栈信息：" +
                    "\n${throwable.printStackTrace()}")
        }
    }

    fun writeString(key: String,value: String){
        try {
            sp.putString(key,value)
            sp.apply()
        } catch (throwable: Throwable) {
            Log.e("PrefUtil","message : ${throwable.message} \ntoString() : " +
                    "${throwable.toString()}\nprintStackTrace() 输出堆栈信息：" +
                    "\n${throwable.printStackTrace()}")
        }
    }

    fun writeFloat(key: String,value: Float){
        try {
            sp.putFloat(key,value)
            sp.apply()
        } catch (throwable: Throwable) {
            Log.e("PrefUtil","message : ${throwable.message} \ntoString() : " +
                    "${throwable.toString()}\nprintStackTrace() 输出堆栈信息：" +
                    "\n${throwable.printStackTrace()}")
        }
    }

    fun writeLong(key: String,value: Long){
        try {
            sp.putLong(key,value)
            sp.apply()
        } catch (throwable: Throwable) {
            Log.e("PrefUtil","message : ${throwable.message} \ntoString() : " +
                    "${throwable.toString()}\nprintStackTrace() 输出堆栈信息：" +
                    "\n${throwable.printStackTrace()}")
        }
    }

    fun readInt(key: String,failValue: Int): Int{
        try {
            return spget.getInt(key,failValue)
        } catch (throwable: Throwable) {
            Log.e("PrefUtil","message : ${throwable.message} \ntoString() : " +
                    "${throwable.toString()}\nprintStackTrace() 输出堆栈信息：" +
                    "\n${throwable.printStackTrace()}")
            return failValue
        }
    }

    fun readFloat(key: String,failValue: Float): Float{
        try {
            return spget.getFloat(key,failValue)
        } catch (throwable: Throwable) {
            Log.e("PrefUtil","message : ${throwable.message} \ntoString() : " +
                    "${throwable.toString()}\nprintStackTrace() 输出堆栈信息：" +
                    "\n${throwable.printStackTrace()}")
            return failValue
        }
    }
    fun readBoolean(key: String,failValue: Boolean): Boolean{
        try {
            return spget.getBoolean(key,failValue)
        } catch (throwable: Throwable) {
            Log.e("PrefUtil","message : ${throwable.message} \ntoString() : " +
                    "${throwable.toString()}\nprintStackTrace() 输出堆栈信息：" +
                    "\n${throwable.printStackTrace()}")
            return failValue
        }
    }
    fun readString(key: String,failValue: String?): String?{
        try {
            return spget.getString(key,failValue)
        } catch (throwable: Throwable) {
            Log.e("PrefUtil","message : ${throwable.message} \ntoString() : " +
                    "${throwable.toString()}\nprintStackTrace() 输出堆栈信息：" +
                    "\n${throwable.printStackTrace()}")
            return failValue
        }
    }
    fun readLong(key: String,failValue: Long): Long{
        try {
            return spget.getLong(key,failValue)
        } catch (throwable: Throwable) {
            Log.e("PrefUtil","message : ${throwable.message} \ntoString() : " +
                    "${throwable.toString()}\nprintStackTrace() 输出堆栈信息：" +
                    "\n${throwable.printStackTrace()}")
            return failValue
        }
    }
}