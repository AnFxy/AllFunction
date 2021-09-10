package cn.com.fenrir.allfunction.model.bean

import cn.com.fenrir.allfunction.util.DateUtils

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/24
 */

/**日期： 几年几月几日  星期几  几点几分, 这样userCourse表就包含了userid,courseid,daynow dayofweek timenow
 * 同时MyDate的属性也可以定义为别的对象，如果属性之间名字相同你可以使用 @Embedded(prefix = )属性确保唯一
 * */
data class MyDate(
    val dayNow: String = DateUtils.getDayNow(),
    val dayofWeek: String = DateUtils.getDayofWeek(),
    val timeNow: String = DateUtils.getTimeNow()
)