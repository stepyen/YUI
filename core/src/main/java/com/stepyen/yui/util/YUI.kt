package com.stepyen.yui.util

import android.app.Application
import android.content.Context
import com.stepyen.yui.util.YUI
import com.stepyen.yui.util.L

/**
 * UI全局设置
 */
object YUI {

    private lateinit var sContext: Application

    fun init(context: Application) {
        sContext = context
    }


    fun getContext(): Context{

        if (sContext == null) {
            throw ExceptionInInitializerError("请先在全局Application中调用 XUI.init() 初始化！")
        }

        return sContext
    }


    /**
     * 设置调试模式
     *
     * @param isDebug
     * @return
     */
    fun debug(isDebug: Boolean) {
        L.setLog(isDebug)
    }
}