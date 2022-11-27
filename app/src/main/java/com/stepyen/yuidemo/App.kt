package com.stepyen.yuidemo

import android.app.Application
import com.stepyen.yui.util.YUI

/**
 * date：2022/10/28
 * author：stepyen
 * description：
 *
 */
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        YUI.init(this)
        YUI.debug(true)
    }
}