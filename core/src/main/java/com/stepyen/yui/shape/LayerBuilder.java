package com.stepyen.yui.shape;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;

/**
 * date：2018/9/29
 * author：stepyen
 * description：
 *
 *
 * [原创]android使用代码生成LayerDrawable的方法和注意事项
 *  https://www.cnblogs.com/carbs/p/5302908.html

 */
public class LayerBuilder {
    private LayerDrawable drawable;

    public LayerBuilder(Drawable[] drawables) {
        drawable = new LayerDrawable(drawables);
    }

    public static LayerBuilder create(Drawable... drawables) {
        return new LayerBuilder(drawables);
    }

    public LayerBuilder left(int index, int px) {
        drawable.setLayerInset(index, px, 0, 0, 0);
        return this;
    }

    public LayerBuilder top(int index, int px) {
        drawable.setLayerInset(index, 0, px, 0, 0);
        return this;
    }

    public LayerBuilder right(int index, int px) {
        drawable.setLayerInset(index, 0, 0, px,0 );
        return this;
    }

    public LayerBuilder bottom(int index, int px) {
        drawable.setLayerInset(index, 0, 0, 0, px);
        return this;
    }

    public LayerBuilder setLayerInset(int index, int left, int top, int right, int bottom) {
        drawable.setLayerInset(index, left, top, right, bottom);
        return this;
    }

    public void build(View view) {
        view.setBackground(drawable);
    }

    public Drawable build() {
        return drawable;
    }
}
