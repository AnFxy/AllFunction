package cn.com.fenrir.allfunction.model.database.table

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import cn.com.fenrir.allfunction.model.bean.MyDate

/**
 * 课题号（GORASA-XXXX)
 * room中，如果两个表具有一对一，一对多，多对多的关系，那么如果需要连表查询的话，需要再建立一个数据数据类
 * 学生表，课程表，选课表，学生表和课程表之间没有直接的联系，学生表和选课表、课程表和选课表之间的关系是多对多，一个学生可以选多门课程，一门课程可以被多名学生选择
 * 按照查询规则，1.查询所有学生以及学生所选的课程  2.查询所有课程以及每门课程被选的学生们
 * 所以需要创建两个关系数据类
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/18
 */
@Entity(tableName = "user_course_table", primaryKeys = ["user_id","course_id"])
data class UserCourseTable(
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "course_id") val courseId: Int,
    @Embedded val chooseDate: MyDate
)
