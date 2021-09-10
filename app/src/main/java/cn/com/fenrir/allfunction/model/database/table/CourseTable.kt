package cn.com.fenrir.allfunction.model.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/18
 */
@Entity(tableName = "course_table")
data class CourseTable(@PrimaryKey @ColumnInfo(name = "course_id") val id: Int,
                  @ColumnInfo(name = "course_name") val name: String)