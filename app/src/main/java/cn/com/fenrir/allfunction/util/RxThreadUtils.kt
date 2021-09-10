package cn.com.fenrir.allfunction.util

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 课题号（GORASA-XXXX)
 * 类或接口的描述信息 https://www.jianshu.com/p/45309538ad94
 * @author xiaoyun.fangfenrir-inc.com.cn
 * @date 2021/8/18
 */
object RxThreadUtils {
    /**
     * Observable 切换到主线程
     */
    fun <T> observableToMain(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * Flowable 切换到主线程,与observable相似，但支持背压，适用于当上游发送速度较快时
     * 能够发射0或n个数据，并以成功或错误事件终止。 支持Backpressure，可以控制数据源发射的速度。
     */
    fun <T> flowableToMain(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * Maybe 切换到主线程，能够发射0或者1个数据，要么成功，要么失败
     */
    fun <T> maybeToMain(): MaybeTransformer<T, T> {
        return MaybeTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * completable 切换到主线程，它从来不发射数据，只处理 onComplete 和 onError 事件。可以看成是Rx的Runnable。
     */
    fun  completeToMain(): CompletableTransformer {
        return CompletableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * Single 切换到主线程，只发射单个数据或错误事件
     */
    fun <T> singleToMain(): SingleTransformer<T,T> {
        return SingleTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

}