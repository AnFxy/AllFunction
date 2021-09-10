package cn.com.fenrir.allfunction.model.database.dao

import androidx.room.*
import cn.com.fenrir.allfunction.model.database.table.UserCourseTable
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/23
 */
@Dao
interface UserCourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userCourse: UserCourseTable): Completable

    @Delete
    fun delete(usrCourse: UserCourseTable): Completable

    @Update
    fun update(userCourse: UserCourseTable): Completable

    /**查询所有学生的选课情况*/
    @Query("SELECT user_table.user_name As userName," +
            " course_table.course_name As courseName, " +
            "user_course_table.dayNow As dayNow FROM user_table, " +
            "user_course_table, course_table " +
            "WHERE user_table.user_id = user_course_table.user_id " +
            "AND course_table.course_id = user_course_table.course_id " +
            "ORDER BY user_table.user_id")
    fun getUsersCourses(): Flowable<List<UsersCourses>>

    data class UsersCourses(val userName: String, val courseName: String, val dayNow: String)
}