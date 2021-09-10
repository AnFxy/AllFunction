package cn.com.fenrir.allfunction.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import cn.com.fenrir.allfunction.R
import cn.com.fenrir.allfunction.databinding.ActivityLoginBinding
import cn.com.fenrir.allfunction.util.DialogUtils
import cn.com.fenrir.allfunction.util.StringUtils
import cn.com.fenrir.allfunction.viewmodel.LoginActivityViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity() {
    lateinit var bind : ActivityLoginBinding
    private val viewModel by lazy {  ViewModelProviders.of(this).get(LoginActivityViewModel::class.java)}
    lateinit var dispose : Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bind = DataBindingUtil.setContentView(this,R.layout.activity_login)
        initView()
    }

    private fun initView(){
        checkPasswordIsLegal()/**核查账号是否合法*/
        checkCountIsLegal()/**核查密码是否合法**/
        bind.btnLogin.setOnClickListener { /*点击登录按钮将会出现以下操作*/
            DialogUtils.showLoginDialog(this)
            doTheLogin()
        }
        viewModel.useData.observe(this, {
            it.flatMap {
                Observable.just(it.status)
            }.subscribe({
                if (it == "success"){
                    gotoMainActivity()
                }else{
                    Log.d("aaaaaa","count and password is failed!")
                }
            },{
                it.printStackTrace()
            })
        })
    }

    private fun doTheLogin(){
        val count = bind.editCount.text.toString()
        val password = bind.editPassword.text.toString()/**由于服务器那边出现了一些问题，只有密码合法可以登录**/
        viewModel.getUserDataFromService(count,"12345678901")
    }

    private fun gotoMainActivity(){
        dispose = Single.just(1)
            .delay(1500,TimeUnit.MILLISECONDS)
            .subscribe({
                createIntent(MainActivity::class.java)
            },{
                Toast.makeText(this,"延时进入主活动异常",Toast.LENGTH_SHORT).show()
            })
    }

    private fun checkPasswordIsLegal(){/**用户输入密码时时进行检测*/
        bind.editPassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                val strPassword = p0.toString()
                if (strPassword==""){
                    showInitIconAndStroke(bind.editPassword,bind.ivCheckIconPassword)
                }else{
                    val isLegal = StringUtils.checkPassword(strPassword,6,10)
                    if (isLegal){
                        showRightIconAndStroke(bind.editPassword,bind.ivCheckIconPassword)
                    }else{
                        showWrongIconAndStroke(bind.editPassword,bind.ivCheckIconPassword)
                    }
                }
                checkCanLogin()
            }
        })
    }

    private fun checkCountIsLegal(){/**用户输入账号时进行检测*/
        bind.editCount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                val strCount = p0.toString()
                if(strCount.length != 11){
                    if (strCount!= ""){
                        showWrongIconAndStroke(bind.editCount,bind.ivCheckIconCount)
                    }else{
                        showInitIconAndStroke(bind.editCount,bind.ivCheckIconCount)
                    }
                }else{
                    showRightIconAndStroke(bind.editCount,bind.ivCheckIconCount)
                }
                checkCanLogin()
            }
        })
    }

    fun showWrongIconAndStroke(edit: EditText, imag: ImageView){
        edit.background = AppCompatResources.getDrawable(this,R.drawable.edit_text_wrong)
        imag.setImageResource(R.drawable.icon_check_wrong)
    }

    fun showRightIconAndStroke(edit: EditText, imag: ImageView){
        edit.background = AppCompatResources.getDrawable(this,R.drawable.edit_text_selector)
        imag.setImageResource(R.drawable.icn_select_checked)
    }

    fun showInitIconAndStroke(edit: EditText, imag: ImageView){
        edit.background = AppCompatResources.getDrawable(this,R.drawable.edit_text_selector)
        imag.setImageResource(R.drawable.icn_select)
    }

    fun checkCanLogin(){
        val imgLegal = AppCompatResources.getDrawable(this,R.drawable.icn_select_checked)?.constantState
        val imgPassword = bind.ivCheckIconPassword.drawable.constantState
        val imgCount = bind.ivCheckIconCount.drawable.constantState
        if (imgPassword==imgLegal && imgCount==imgLegal){
            bind.btnLogin.background = AppCompatResources.getDrawable(this,R.drawable.button_login_selector)
            bind.btnLogin.setTextColor(Color.parseColor("#ffffff"))
            bind.btnLogin.isEnabled = true
        }else{
            bind.btnLogin.background = AppCompatResources.getDrawable(this,R.drawable.button_login_normal)
            bind.btnLogin.setTextColor(Color.parseColor("#999999"))
            bind.btnLogin.isEnabled = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dispose.dispose()
    }
}