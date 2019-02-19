package com.kaixugege.festec

import android.os.Bundle
import com.kaixugege.latte_core.activites.ProxyActivity
import com.kaixugege.latte_core.delegates.LatteDelegate

class ExampleActivity : ProxyActivity() {
    override fun setRootDelegate(): LatteDelegate {
        return ExampleDelegate()
    }


}
