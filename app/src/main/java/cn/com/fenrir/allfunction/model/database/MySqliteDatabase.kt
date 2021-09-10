package cn.com.fenrir.allfunction.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cn.com.fenrir.allfunction.model.database.dao.CourseDao
import cn.com.fenrir.allfunction.model.database.dao.UserCourseDao
import cn.com.fenrir.allfunction.model.database.dao.UserDao
import cn.com.fenrir.allfunction.model.database.table.CourseTable
import cn.com.fenrir.allfunction.model.database.table.UserCourseTable
import cn.com.fenrir.allfunction.model.database.table.UserTable

/**
 * 课题号（GORASA-XXXX)
 * 数据库的定义: Room数据库必须是一个继承自RoomDatabase的抽象类。通常情况下应用内应该只有一个Room数据库实例。
 * entity实体表是一个list，可以有多个
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/17
 */
@Database(entities = [UserTable::class, CourseTable::class, UserCourseTable::class],version = 1,exportSchema = false)
abstract class MySqliteDatabase : RoomDatabase(){
    /*方法获取到Dao类*/
    abstract fun useDao(): UserDao

    abstract fun courseDao(): CourseDao

    abstract fun userCourseDao(): UserCourseDao

    /**单例数据库定义*/
    companion object{

        @JvmStatic
        @Volatile
        private var INSTANCE: MySqliteDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): MySqliteDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            /**线程安全锁*/
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,MySqliteDatabase::class.java,"my_sqlite_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}