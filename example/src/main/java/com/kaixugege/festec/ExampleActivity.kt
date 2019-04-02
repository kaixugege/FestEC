package com.kaixugege.festec

import android.os.Bundle
import com.kaixugege.latte.ec.lanucher.LauncherDelegate
import com.kaixugege.latte.ec.sign.SignInDelegate
import com.kaixugege.latte.ec.sign.SignUpDelegate
import com.kaixugege.latte_core.activites.ProxyActivity
import com.kaixugege.latte_core.delegates.LatteDelegate

class ExampleActivity : ProxyActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar;
        if (actionBar != null){
            actionBar.hide()
        }
    }

    override fun setRootDelegate(): LatteDelegate {
//        return LauncherDelegate()
        return SignInDelegate()
    }

}
