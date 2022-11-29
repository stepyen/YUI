package com.stepyen.yui.shape;

import android.graphics.drawable.GradientDrawable;
import android.view.View;

/**
 * date：2018/9/29
 * author：stepyen
 * description：
 */
public interface IShape<T extends IShape> {

    T shape(int shape);

    T stroke(float width, int color);

    T stroke(float width, int color, float dashWidth, float dashGap);

    T solid(int color);

    T radius(float radius);

    T radius(float topLeft, float topRight, float bottomLeft, float bottomRight);

    T gradient(GradientDrawable.Orientation orientation, int startColor, int centerColor, int endColor);

    T gradient(GradientDrawable.Orientation orientation, int startColor, int endColor);

    T gradientType(int type);

    T gradientCenter(float x, float y);

    T gradientRadius(float radius);

    T setSize(int width, int height);

    void build(View v);

    GradientDrawable build();
}
