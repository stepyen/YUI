package com.stepyen.yui.shape;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

/**
 * date：2018/9/29
 * author：stepyen
 * description：
 */
public class ShapeListBuilder {
    private StateListDrawable drawable;
    private Drawable mDefaltDrawable;   // 默认的 Drawable

    private ShapeListBuilder(Drawable drawable) {
        this.drawable = new StateListDrawable();
        this.mDefaltDrawable = drawable;
    }

    /**
     * @param drawable 默认状态下的drawable
     */
    public static ShapeListBuilder create(Drawable drawable) {
        return new ShapeListBuilder(drawable);
    }

    /**
     * 添加状态
     * @param shape 状态对应的shape
     * @param state 状态类型     android.R.attr.state_pressed
     *              （这里要注意添加的顺序，只要有一个状态与之相配，背景就会被换掉。
     *              所以不要把大范围放在前面了，会造成没有什么效果了。）
     */
    public ShapeListBuilder addShape(Drawable shape, int... state) {
        drawable.addState(state, shape);
        return this;
    }

    /**
     * 设置背景，记得实现onClick事件监听，修改对应状态，不然无效果
     */
    public void build(View v) {
        addShape(mDefaltDrawable);
        v.setBackground(drawable);
    }

    public StateListDrawable build() {
        addShape(mDefaltDrawable);
        return drawable;
    }
}
