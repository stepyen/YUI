package com.stepyen.yui.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.annotation.DrawableRes
import android.os.Build
import androidx.annotation.DimenRes
import androidx.annotation.ColorRes
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import java.lang.UnsupportedOperationException

/**
 * 获取res中的资源
 */
class ResUtils private constructor() {

    companion object {
        /**
         * 获取resources对象
         *
         * @return
         */
        val resources: Resources
            get() = YUI.getContext().resources

        /**
         * 获取字符串
         *
         * @param resId
         * @return
         */
        fun getString(@StringRes resId: Int): String {
            return resources.getString(resId)
        }

        /**
         * 获取资源图片
         *
         * @param resId
         * @return
         */
        fun getDrawable(@DrawableRes resId: Int): Drawable? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                YUI.getContext().getDrawable(resId)
            } else resources.getDrawable(resId)
        }

        /**
         * 获取资源图片【和主体有关】
         *
         * @param resId
         * @return
         */
        fun getDrawable(context: Context, @DrawableRes resId: Int): Drawable? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                context.getDrawable(resId)
            } else context.resources.getDrawable(resId)
        }

        /**
         * 获取dimes值，返回的是精确的值
         *
         * @param resId
         * @return
         */
        fun getDimens(@DimenRes resId: Int): Float {
            return resources.getDimension(resId)
        }

        /**
         * 获取Color值
         *
         * @param resId
         * @return
         */
        fun getColor(@ColorRes resId: Int): Int {
            return resources.getColor(resId)
        }

        /**
         * 获取ColorStateList值
         *
         * @param resId
         * @return
         */
        fun getColors(@ColorRes resId: Int): ColorStateList {
            return resources.getColorStateList(resId)
        }

        /**
         * 获取dimes值，返回的是【去余取整】的值
         *
         * @param resId
         * @return
         */
        fun getDimensionPixelOffset(@DimenRes resId: Int): Int {
            return resources.getDimensionPixelOffset(resId)
        }

        /**
         * 获取dimes值，返回的是【4舍5入】的值
         *
         * @param resId
         * @return
         */
        fun getDimensionPixelSize(@DimenRes resId: Int): Int {
            return resources.getDimensionPixelSize(resId)
        }

        /**
         * 获取字符串的数组
         *
         * @param resId
         * @return
         */
        fun getStringArray(@ArrayRes resId: Int): Array<String> {
            return resources.getStringArray(resId)
        }

        /**
         * 获取数字的数组
         *
         * @param resId
         * @return
         */
        fun getIntArray(@ArrayRes resId: Int): IntArray {
            return resources.getIntArray(resId)
        }
    }

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }
}