package cn.com.fenrir.allfunction.net.`interface`

import cn.com.fenrir.allfunction.model.bean.User
import cn.com.fenrir.allfunction.model.bean.UserBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/13
 */
interface LoginService {

    @GET("authenticate?")
    fun getCall(@Query("username") username: String, @Query("mobile") password: String):Observable<UserBean>
}