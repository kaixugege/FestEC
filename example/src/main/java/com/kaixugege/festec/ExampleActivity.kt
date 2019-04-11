package com.kaixugege.festec

import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import com.kaixugege.latte.ec.lanucher.LauncherDelegate
import com.kaixugege.latte.ec.sign.SignInDelegate
import com.kaixugege.latte.ec.sign.SignUpDelegate
import com.kaixugege.latte_core.activites.ProxyActivity
import com.kaixugege.latte_core.delegates.LatteDelegate
import com.kaixugege.latte_core.ui.launcher.OnLauncherFinshTag

class ExampleActivity : ProxyActivity() {
    override fun onLauncherFinsh(tag: OnLauncherFinshTag?) {
        when (tag) {
            OnLauncherFinshTag.NOT_SINGED -> {
                Toast.makeText(this,"启动结束，用户没登陆",Toast.LENGTH_SHORT).show()
                startWithPop(SignInDelegate())//没有登陆，进入注册页面
            }
            OnLauncherFinshTag.SINGED -> {
                Toast.makeText(this,"启动结束，用户登陆了",Toast.LENGTH_SHORT).show()
                startWithPop(ExampleDelegate())//没有登陆，进入注册页面
            }
            else -> {
            }
        }

    }


    override fun onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    override fun onSignInSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar;
        if (actionBar != null) {
            actionBar.hide()
        }
    }

    override fun setRootDelegate(): LatteDelegate {
        return LauncherDelegate()
//        return SignInDelegate()
    }

}
