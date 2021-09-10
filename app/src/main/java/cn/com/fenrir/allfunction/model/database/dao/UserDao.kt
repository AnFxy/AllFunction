package cn.com.fenrir.allfunction.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import cn.com.fenrir.allfunction.model.database.table.UserTable
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * 课题号（GORASA-XXXX)
 * Dao是用于定义表的操作，包含基本的增删改查
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/17
 * 1.为什么要用suspend关键词？
 * 2.为什么删除和更新需要返回一个int值
 */

@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserTable): Completable

    @Delete
    fun delete(user: UserTable): Completable

    @Update
    fun update(user: UserTable): Completable

    @Query("SELECT * FROM user_table ORDER BY user_id ASC")
    fun getAllDataFromUserTable(): Flowable<List<UserTable>>

    @Query("SELECT * FROM user_table WHERE user_name =:userName")
    fun getSingleData(userName: String): Flowable<UserTable>
}