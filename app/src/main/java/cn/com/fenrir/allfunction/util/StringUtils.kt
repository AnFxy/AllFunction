package cn.com.fenrir.allfunction.util

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/10
 */
object StringUtils {

    fun checkPassword(textPassword: String, minLength: Int, maxLength: Int): Boolean{
        var tag = true
        if (textPassword.length in minLength..maxLength){
            for (i in textPassword){
                if ( (i in 'a'..'z') || (i in 'A'..'Z') || (i in '0'..'9') || i=='*' || i=='_' || i=='@' || i=='!'){
                    tag = true
                }else{
                    tag = false
                    break
                }
            }
        }else{
            tag = false
        }
        return tag
    }


}