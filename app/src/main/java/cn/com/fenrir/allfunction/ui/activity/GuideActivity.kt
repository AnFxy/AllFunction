package cn.com.fenrir.allfunction.ui.activity


import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import cn.com.fenrir.allfunction.R
import cn.com.fenrir.allfunction.databinding.ActivityGuideBinding
import cn.com.fenrir.allfunction.ui.adapter.MyViewPagerAdapter

/**引导页面，用于提示用户如何选择**/
class GuideActivity : BaseActivity() {
    lateinit var bind: ActivityGuideBinding

    private val listInt = arrayListOf(R.drawable.guide_page,R.drawable.guide_page,
        R.drawable.guide_page,R.drawable.guide_page,R.drawable.guide_page,R.drawable.guide_page)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
        bind = DataBindingUtil.setContentView(this,R.layout.activity_guide)
        initView()
    }
    private fun initView(){
        val adapter = MyViewPagerAdapter()
        adapter.refreshData(listInt)
        bind.vpGuide.adapter = adapter
        bind.vpGuide.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (position==0) bind.tvLeft.visibility = View.INVISIBLE else   bind.tvLeft.visibility = View.VISIBLE
                if (position==5) bind.tvRight.text = "进入" else  bind.tvRight.text = "下一个"
                bind.ivChooseCircle.x = bind.llCircle.getChildAt(position).x
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
        bind.llCircle.getChildAt(0).post(Runnable {
            bind.ivChooseCircle.x = bind.llCircle.getChildAt(0).x
        })
        /**点击上一个，切换到上一个，点击下一个，切换到下一个*/
        bind.tvLeft.setOnClickListener {
            bind.vpGuide.setCurrentItem(bind.vpGuide.currentItem-1)

        }
        bind.tvRight.setOnClickListener {
            if(bind.vpGuide.currentItem == 5){
                createIntent(LoginActivity::class.java)
            }else{
                bind.vpGuide.setCurrentItem(bind.vpGuide.currentItem+1)
            }
        }

    }

}