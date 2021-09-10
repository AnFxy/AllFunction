package cn.com.fenrir.allfunction.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import cn.com.fenrir.allfunction.R
import cn.com.fenrir.allfunction.databinding.ActivityMainBinding
import cn.com.fenrir.allfunction.ui.fragment.HomeFragment
import cn.com.fenrir.allfunction.ui.fragment.PersonFragment
import cn.com.fenrir.allfunction.ui.fragment.SettingFragment
import cn.com.fenrir.allfunction.util.Constants
import cn.com.fenrir.allfunction.util.ContextAll
import cn.com.fenrir.allfunction.util.PrefUtil
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.review.testing.FakeReviewManager
import com.hjq.bar.OnTitleBarListener

class MainActivity : BaseActivity() {
    lateinit var bind: ActivityMainBinding
    var exitTime: Long = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initView()
    }

    fun initView(){
        val newVersionCode = ContextAll.getVersionCode()
        PrefUtil.writeLong(Constants.OLD_VERSION_CODE,newVersionCode)
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        val arrayFrag = arrayListOf<Fragment>(HomeFragment(),PersonFragment(),SettingFragment())
        transaction.add(R.id.fl_main,arrayFrag[0]).add(R.id.fl_main,arrayFrag[1]).add(R.id.fl_main,arrayFrag[2]).hide(arrayFrag[1]).hide(arrayFrag[2]).commit()

        bind.toolBar.setOnTitleBarListener(object: OnTitleBarListener{
            override fun onLeftClick(view: View?) {
            }
            override fun onTitleClick(view: View?) {
            }
            override fun onRightClick(view: View?) {
            }
        })

        bind.nbBottom.setOnNavigationItemSelectedListener {
            val transactionClick: FragmentTransaction = manager.beginTransaction()
            when(it.itemId){
                R.id.item_bottom_1->{
                    transactionClick.show(arrayFrag[0]).hide(arrayFrag[1]).hide(arrayFrag[2])
                }
                R.id.item_bottom_2->{
                    transactionClick.hide(arrayFrag[0]).show(arrayFrag[1]).hide(arrayFrag[2])
                }
                R.id.item_bottom_3->{
                    transactionClick.hide(arrayFrag[0]).hide(arrayFrag[1]).show(arrayFrag[2])
                }
                else->{}
            }
            transactionClick.commit()
            true
        }
    }

    override fun onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        }else{
            closeApp()
        }
    }
}