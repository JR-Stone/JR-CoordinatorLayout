package com.stone.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.appbar.AppBarLayout;

public class AppBarBehavior extends AppBarLayout.Behavior {
    /**
     * nestedScrollview的滑动距离
     */
    int ay = 0;
    /**
     * nestedScrollview 沒有滑动到顶部之前的可滑动最大距离
     */
    private int minY = 0;

    public AppBarBehavior() {

    }

    public AppBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * AppBarLayout布局时调用
     *
     * @param parent          父布局CoordinatorLayout
     * @param abl             使用此Behavior的AppBarLayout
     * @param layoutDirection 布局方向
     * @return 返回true表示子View重新布局，返回false表示请求默认布局
     */
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, AppBarLayout abl, int layoutDirection) {
        return super.onLayoutChild(parent, abl, layoutDirection);
    }

    /**
     * 当CoordinatorLayout的子View尝试发起嵌套滚动时调用
     *
     * @param parent            父布局CoordinatorLayout
     * @param child             使用此Behavior的AppBarLayout
     * @param directTargetChild CoordinatorLayout的子View，或者是包含嵌套滚动操作的目标View
     * @param target            发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     * @param nestedScrollAxes  嵌套滚动的方向
     * @return 返回true表示接受滚动
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes, int type) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }


    /**
     * 当嵌套滚动已由CoordinatorLayout接受时调用
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param child             使用此Behavior的AppBarLayout
     * @param directTargetChild CoordinatorLayout的子View，或者是包含嵌套滚动操作的目标View
     * @param target            发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     */
    @Override
    public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull AppBarLayout child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    /**
     * 当准备开始嵌套滚动时调用
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param child             使用此Behavior的AppBarLayout
     * @param target            发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     * @param dx                用户在水平方向上滑动的像素数
     * @param dy                用户在垂直方向上滑动的像素数
     * @param consumed          输出参数，consumed[0]为水平方向应该消耗的距离，consumed[1]为垂直方向应该消耗的距离
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed, int type) {
        if (minY == 0) {
            minY = target.getHeight() + child.getHeight() - coordinatorLayout.getHeight();
        }
        /**
         * 如果nestedScrollview 布局内容少没有内部滑动，这时需要我们进行处理
         * 当nestedScrollview 内容很多时，我们不需要任何事情
         * */
        if (target.getScrollY() <= 0) {
            ay += dy;
            if (dy > 0 && ay >= minY) {
                dy = minY + dy - ay;//这里之所以没有让dy = 0;因为当快速滑动时就会有问题，快速滑动时dy的变化很大，自行琢磨一下，还不懂加群交流
                ay = minY;
            }
            if (dy < 0 && ay <= 0) {
                ay = 0;
                dy = 0;
            }
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }

    /**
     * 嵌套滚动时调用
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param child             使用此Behavior的AppBarLayout
     * @param target            发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     * @param dxConsumed        由目标View滚动操作消耗的水平像素数
     * @param dyConsumed        由目标View滚动操作消耗的垂直像素数
     * @param dxUnconsumed      由用户请求但是目标View滚动操作未消耗的水平像素数
     * @param dyUnconsumed      由用户请求但是目标View滚动操作未消耗的垂直像素数
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    }

    /**
     * 当嵌套滚动的子View准备快速滚动时调用
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param child             使用此Behavior的AppBarLayout
     * @param target            发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     * @param velocityX         水平方向的速度
     * @param velocityY         垂直方向的速度
     * @return 如果Behavior消耗了快速滚动返回true
     */
    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY) {

        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    /**
     * 当嵌套滚动的子View快速滚动时调用
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param child             使用此Behavior的AppBarLayout
     * @param target            发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     * @param velocityX         水平方向的速度
     * @param velocityY         垂直方向的速度
     * @param consumed          如果嵌套的子View消耗了快速滚动则为true
     * @return 如果Behavior消耗了快速滚动返回true
     */
    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    /**
     * 当触摸时调用
     *
     * @param parent 父布局CoordinatorLayout
     * @param child  使用此Behavior的AppBarLayout
     * @param ev     手势事件
     */
    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev) {
        return super.onTouchEvent(parent, child, ev);
    }

    /**
     * 当触摸想要拦截时调用   关键所在 true 不拦截  false 拦截AppBarLayout的手势触摸
     *
     * @param parent 父布局CoordinatorLayout
     * @param child  使用此Behavior的AppBarLayout
     * @param ev     手势事件
     */
    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev) {
        return false;
    }

    /**
     * 嵌套滚动结束时被调用
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param abl               使用此Behavior的AppBarLayout
     * @param target            发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     */
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, abl, target, type);
    }

}
