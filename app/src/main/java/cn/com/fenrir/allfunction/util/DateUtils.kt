package cn.com.fenrir.allfunction.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/24
 */
object DateUtils {

    val weekDays = arrayListOf("Monday", "TuesDay", "ThursDay", "Friday", "SaturDay", "SunDay")

    /**获取当前是几年几月几日*/
    fun getDayNow(): String {
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            return SimpleDateFormat("yyyy-MM-dd").format(Date())
        } else {
            var tms = Calendar.getInstance()
            return tms.get(Calendar.YEAR).toString() + "-" + tms.get(Calendar.MONTH)
                .toString() + "-" + tms.get(Calendar.DAY_OF_MONTH).toString()
        }
    }

    /**获取当前是几时几分几秒*/
    fun getTimeNow(): String {
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            return SimpleDateFormat("HH: mm: ss").format(Date())
        } else {
            var tms = Calendar.getInstance()
            return tms.get(Calendar.HOUR_OF_DAY).toString() + ":" + tms.get(Calendar.MINUTE)
                .toString() + ":" + tms.get(Calendar.SECOND).toString()
        }
    }

    /**获取今天是星期几*/
    fun getDayofWeek() = weekDays[Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1]

    /**获取两个日期 2021-02-06与 2023-05-07之间相差几天*/
    fun getDays(date1: String, date2: String): Long {
        val dft = SimpleDateFormat("yyyy-MM-dd")
        try {
            val startTime = dft.parse(date1).time//开始时间
            val endTime = dft.parse(date2).time//结束时间
            val num = (endTime - startTime) / 24 / 60 / 60 / 1000//时间戳相差的毫秒数换算成天数
            return num
        } catch (e: ParseException) {
            e.printStackTrace()
            return 0L
        }
    }
}