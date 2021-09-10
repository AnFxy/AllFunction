package cn.com.fenrir.allfunction.util

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/17
 */
object RamdomUidUtil {

    fun getUid(): String{
        val userid = java.util.UUID.randomUUID().toString().replace("-","").toUpperCase()
        return userid
    }
}