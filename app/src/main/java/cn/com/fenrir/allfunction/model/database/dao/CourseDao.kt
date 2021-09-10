package cn.com.fenrir.allfunction.model.database.dao

import androidx.room.*
import cn.com.fenrir.allfunction.model.database.table.CourseTable
import cn.com.fenrir.allfunction.model.database.table.UserTable
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/23
 */
@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(course: CourseTable): Completable

    @Delete
    fun delete(course: CourseTable): Completable

    @Update
    fun update(course: CourseTable): Completable

    @Query("SELECT * FROM course_table ORDER BY course_id ASC")
    fun getAllDataFromCourseTable(): Flowable<List<CourseTable>>

    @Query("SELECT * FROM course_table WHERE course_id =:courseId")
    fun getSingleData(courseId: Int): Flowable<CourseTable>
}