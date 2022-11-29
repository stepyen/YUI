package com.stepyen.yui.shape;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.View;

import com.stepyen.yui.util.YUI;

import androidx.core.content.ContextCompat;

/**
 * date：2018/9/29
 * author：stepyen
 * description：
 */
public class ShapeBuilder implements IShape<ShapeBuilder> {
    private GradientDrawable drawable;
    private Context mContext;

    private ShapeBuilder(Context context) {
        this.mContext = context;
        drawable = new GradientDrawable();
        // 默认形状是矩形
        drawable.setShape(GradientDrawable.RECTANGLE);
    }

    public static ShapeBuilder create() {
        return new ShapeBuilder(YUI.INSTANCE.getContext());
    }
    /**
     * 设置形状
     * @param shape GradientDrawable.RECTANGLE, OVAL, LINE, RING
     * @return
     */
    @Override
    public ShapeBuilder shape(int shape) {
        drawable.setShape(shape);
        return this;
    }

    /**
     * 边线
     * @param width
     * @param color
     * @return
     */
    @Override
    public ShapeBuilder stroke(float width, int color) {
        drawable.setStroke(dpToPx(width),getColor(color));
        return this;
    }

    /**
     * 边线
     * @param width 边线的高度
     * @param color
     * @param dashWidth  横线的宽度
     * @param dashGap 横线的间距
     * @return
     */
    @Override
    public ShapeBuilder stroke(float width, int color, float dashWidth, float dashGap) {
        drawable.setStroke(dpToPx(width), getColor(color), dpToPx(dashWidth), dpToPx(dashGap));
        return this;
    }

    /**
     * 背景色
     * @param color
     * @return
     */
    @Override
    public ShapeBuilder solid(int color) {
        drawable.setColor( getColor(color));
        return this;
    }

    @Override
    public ShapeBuilder radius(float radius) {
        drawable.setCornerRadius(dpToPx(radius));
        return this;
    }

    @Override
    public ShapeBuilder radius(float topLeft, float topRight, float bottomRight,float bottomLeft ) {
        topLeft = dpToPx(topLeft);
        topRight = dpToPx(topRight);
        bottomRight = dpToPx(bottomRight);
        bottomLeft = dpToPx(bottomLeft);

        float[] radii = {
                topLeft, topLeft,
                topRight, topRight,
                bottomRight, bottomRight,
                bottomLeft, bottomLeft};

        drawable.setCornerRadii(radii);
        return this;
    }

    @Override
    public ShapeBuilder gradient(GradientDrawable.Orientation orientation, int startColor, int centerColor, int endColor) {
        drawable.setOrientation(orientation);
        drawable.setColors(new int[]{getColor(startColor), getColor(centerColor), getColor(endColor)});
        return this;
    }

    @Override
    public ShapeBuilder gradient(GradientDrawable.Orientation orientation, int startColor, int endColor) {
        drawable.setOrientation(orientation);
        drawable.setColors(new int[]{getColor(startColor), getColor(endColor)});
        return this;
    }

    /**
     * 渐变type
     * @param gradient
     * GradientDrawable.LINEAR_GRADIENT 线性
     * GradientDrawable.RADIAL_GRADIENT 圆
     * GradientDrawable.SWEEP_GRADIENT 扫描
     */
    @Override
    public ShapeBuilder gradientType(int gradient) {
        drawable.setGradientType(gradient);
        return this;
    }
    /**
     *  这两个属性只有在type不为linear情况下起作用。
     * @param x 相对X的渐变位置
     * @param y 相对Y的渐变位置
     */
    @Override
    public ShapeBuilder gradientCenter(float x, float y) {
        drawable.setGradientCenter(x, y);
        return this;
    }

    /**
     * 该属性只有在type="radial"有效
     * @param radius
     * @return
     */
    @Override
    public ShapeBuilder gradientRadius(float radius) {
        drawable.setGradientRadius(dpToPx(radius));
        return this;
    }

    @Override
    public ShapeBuilder setSize(int width, int height) {
        drawable.setSize(dpToPx(width), dpToPx(height));
        return this;
    }

    @Override
    public void build(View v) {
        build();
        v.setBackground(drawable);
    }

    @Override
    public GradientDrawable build() {
        return drawable;
    }

    private int getColor(int colorId) {
        return ContextCompat.getColor(mContext, colorId);
    }

    private int dpToPx(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
    }
}


