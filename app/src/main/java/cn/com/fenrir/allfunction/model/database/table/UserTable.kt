package cn.com.fenrir.allfunction.model.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 课题号（GORASA-XXXX)
 * 数据库user表用于存储用户的基本个人信息，账号、密码，名字、年龄、性别、
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/17
 */

@Entity(tableName = "user_table")/**可以省略tableName，那么表名默认为类名，同样的字段也可以*/
data class UserTable(@PrimaryKey @ColumnInfo(name = "user_id") val id: Int,
                     @ColumnInfo(name = "user_count") val userCount: String,
                     @ColumnInfo(name = "user_password") var passWord: String,
                     @ColumnInfo(name = "user_name") var userName: String,
                     @ColumnInfo(name = "user_fakename") var fakeName: String,
                     @ColumnInfo(name = "user_sex") val sex: Boolean,
                     @ColumnInfo(name = "user_age") val age: Int
           )