package cn.com.fenrir.allfunction.model.bean

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/13
 */
data class UserBean(
    val message: String,
    val status: String,
    val user: User?
)

data class User(
    val created_at: String,
    val id: Int,
    val mobile: String,
    val name: String,
    val updated_at: String
)