# JR-CoordinatorLayout
CoordinatorLayout + AppBarLayout + NestedScrollView 组合使用实现地图背景，滑动悬停华丽效果。仿饿了么地图界面。TextView 实现FloatingActionButton效果

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Arsenal](https://img.shields.io/badge/Arsenal%20-%20SmartRefresh-4cae4c.svg)](https://android-arsenal.com/details/1/6001)
[![JCenter](https://img.shields.io/badge/%20JCenter%20-1.1.0-5bc0de.svg)](https://bintray.com/scwang90/maven/SmartRefreshLayout/_latestVersion)
[![MinSdk](https://img.shields.io/badge/%20MinSdk%20-%2012%2B%20-f0ad4e.svg)](https://android-arsenal.com/api?level=12)
[![Methods](https://img.shields.io/badge/Methods%20%7C%20Size%20-%20784%20%7C%20121%20KB-d9534f.svg)](http://www.methodscount.com/?lib=com.scwang.smartrefresh%3ASmartRefreshLayout%3A1.0.4)

<!-- [![Platform](https://img.shields.io/badge/Platform-Android-f0ad4e.svg)](https://www.android.com) -->
<!-- [![Author](https://img.shields.io/badge/Author-scwang90-11bbff.svg)](https://github.com/scwang90) -->

CoordinatorLayout就是加强版FrameLayout，适合作为应用顶层的布局（必须是根部局），提供交互行为
通过给子View设定Behavior可以实现他们的交互性为。Behavior能实现一系列的交互行为和布局变化，包括侧滑菜单、可滑动删除的UI元素、View之间跟随移动。
常用支持滑动效果的子View有：比如RecyclerView，NestedScrollView、TabLayout等  切记ScrollView是无效的！

AppBarLayout是一个vertical的LinearLayout，其子View应通过setScrollFlags(int)或者xmL中的app:layout_scrollFlags来提供他们的Behavior。
具体的app:layout_scrollFlags有这么几个： scroll, exitUntilCollapsed, enterAlways, enterAlwaysCollapsed, snap
他必须严格地是CoordinatorLayout的子View，不然他一点作用都发挥不出来。
AppBarLayout下方的滑动控件，比如RecyclerView，NestedScrollView（与AppBarLayout同属于CoordinatorLayout的子View,并列的关系，）,必须严格地通过在xml中指出其滑动Behavior来与AppBarLayout进行绑定。
通常这样：app:layout_behavior="@string/appbar_scrolling_view_behavior"

## 特点功能:

 - 支持多点触摸
 - 支持嵌套多层的视图结构 Layout (ConstraintLayout,TabLayout...)
 - 支持和 NestedScrollView 的无缝同步滚动.
 - 支持回弹动画的插值器，实现各种炫酷的动画效果.
 - 支持设置主题来适配任何场景的 App，不会出现炫酷但很尴尬的情况.

## 完整效果图
|![](https://github.com/JR-Stone/img/blob/master/coordinator/coor1.jpg)|![](https://github.com/JR-Stone/img/blob/master/coordinator/coor2.jpg)|

## Demo
[下载 APK-Demo](https://github.com/JR-Stone/img/blob/master/apk/app-coor.apk)

## 简单使用
人生的第一次写文章呀！还不知道我这第一次会被谁给夺走...还请多多指教，手下留情，少喷少喷！废话不多说，咱们言归正传，其实这三个控件组合可以制作出很多炫酷的界面，不过今天主要分享一下以地图为背景手动滑动透明AppBar渐变的效果，和之前的老版（饿了么）APP运输中订单详情界面一样；我们主要分享一下技术的难点，关于界面的布局及细节上的美化就留给各位开发者自己布局。先上代码和基本效果图

#### 1. layout布局activity_main.xml
``` xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <com.amap.api.maps.MapView
        android:id="@+id/mapView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <android.support.design.widget.AppBarLayout
        android:id="@+id/appBar"
        android:layout_width="match_parent"
        android:layout_height="@dimen/scale_fourHundred_fifty"
        app:elevation="0dp">

        <android.support.design.widget.CollapsingToolbarLayout
            android:id="@+id/collapsingLayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:scrollbarStyle="insideInset"
            app:collapsedTitleGravity="center"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">

            <android.support.v7.widget.Toolbar
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                app:layout_collapseMode="pin"
                app:layout_scrollFlags="scroll" />

        </android.support.design.widget.CollapsingToolbarLayout>
    </android.support.design.widget.AppBarLayout>

    <android.support.v4.widget.NestedScrollView
        android:id="@+id/nestScrollView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/white"
        android:fillViewport="true"
        android:scrollbars="none"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">

        <LinearLayout
            android:layout_width="match_parent"
            android:paddingTop="@dimen/scale_eighty"
            android:background="@color/white"
            android:layout_height="match_parent">

            <TextView
                android:id="@+id/jumpUrl"
                style="@style/defaultText"
                android:gravity="center"
                android:layout_height="wrap_content"
                android:background="@color/white"
                android:drawableBottom="@drawable/ic_touch_tag"
                android:text="@string/content" />

        </LinearLayout>
    </android.support.v4.widget.NestedScrollView>

    <android.support.design.widget.FloatingActionButton
        android:id="@+id/loadingTime"
        android:layout_width="@dimen/scale_eighty"
        android:layout_height="@dimen/scale_eighty"
        app:layout_anchor="@id/nestScrollView"
        app:layout_anchorGravity="top|center"/>
</android.support.design.widget.CoordinatorLayout>
```
#### 2. java代码 MianActivity.java
```java
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.appBar)
    AppBarLayout appBarLayout;
    @BindView(R.id.nestScrollView)
    NestedScrollView scrollView;
    @BindView(R.id.mapView)
    MapView mapView;

    private CoordinatorLayout.LayoutParams layoutParams;
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        layoutParams = (CoordinatorLayout.LayoutParams) scrollView.getLayoutParams();
        layoutParams.setMargins(30, 0, 30, 0);
        scrollView.setLayoutParams(layoutParams);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float a = (float) 30 / appBarLayout.getTotalScrollRange();
                int side = (int) Math.rint(a * i + 30);
                layoutParams.setMargins(side, 0, side, 0);
                scrollView.setLayoutParams(layoutParams);
                if (Math.abs(i) > 0) {
                    float alpha = (float) Math.abs(i) / appBarLayout.getTotalScrollRange();
                    appBarLayout.setAlpha(alpha);
                    scrollView.getBackground().mutate().setAlpha(Math.round(alpha * 255));
                } else {
                    appBarLayout.setAlpha(0);
                    scrollView.getBackground().mutate().setAlpha(0);
                }
            }
        });
    }
}
```
#### 3. 导入包
```
/*高德地图*/
implementation 'com.amap.api:3dmap:latest.integration'
/*注释*/
implementation 'com.jakewharton:butterknife:8.7.0'
annotationProcessor 'com.jakewharton:butterknife-compiler:8.7.0'
```
|默认|滑动中|置顶|
|:---:|:---:|:---:|
|![](https://github.com/JR-Stone/img/blob/master/coordinator/jr_view.jpg)|![](https://github.com/JR-Stone/img/blob/master/coordinator/jr_view1.jpg)|![](https://github.com/JR-Stone/img/blob/master/coordinator/jr_view2.jpg)|

#### 4. 到此基本的效果已经出来了，如果把地图换成一站图片或者简单的布局那么现在已经可以支持了。但是如果是地图的话，那就有问题了；你会发现AppBarLayout透明部分触摸屏幕滑动地图会很困难，基本上一滑动下面的NestedScrollView就会跟着滑动而地图就不能操作了。看下图...
![](https://github.com/JR-Stone/img/blob/master/coordinator/jr_view_map.jpg)

#### 那这个问题怎么解决呢？其实很简单，那就是重写AppBarLayout.Behavior,大家有时间也可以去研究研究behavior,CoordinatorLayout的很多酷炫的效果都是通过behavior来实现的。同样这里也是要重写AppBarLayout.Behavior中的onInterceptTouchEvent()方法,他的作用就是拦截触摸,其中有个boolean类型的返回值，true 表示不拦截，false表示拦截。默认为true,我们只需要返回false就OK了，就是这么简单。上代码（为了让大家更清楚的了解AppBarLayout.Behavior，我将其他主要方法也列了出来并备注了作用）
```java
/**
 * @author jr
 */
public class AppBarBehavior extends AppBarLayout.Behavior{
    public AppBarBehavior() {
    }

    public AppBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * AppBarLayout布局时调用
     *
     * @param parent 父布局CoordinatorLayout
     * @param abl 使用此Behavior的AppBarLayout
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
     * @param parent 父布局CoordinatorLayout
     * @param child 使用此Behavior的AppBarLayout
     * @param directTargetChild CoordinatorLayout的子View，或者是包含嵌套滚动操作的目标View
     * @param target 发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     * @param nestedScrollAxes 嵌套滚动的方向
     * @return 返回true表示接受滚动
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes, int type) {
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes, type);
    }

    /**
     * 当嵌套滚动已由CoordinatorLayout接受时调用
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param child 使用此Behavior的AppBarLayout
     * @param directTargetChild CoordinatorLayout的子View，或者是包含嵌套滚动操作的目标View
     * @param target 发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     */
    @Override
    public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull AppBarLayout child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    /**
     * 当准备开始嵌套滚动时调用
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param child 使用此Behavior的AppBarLayout
     * @param target 发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     * @param dx 用户在水平方向上滑动的像素数
     * @param dy 用户在垂直方向上滑动的像素数
     * @param consumed 输出参数，consumed[0]为水平方向应该消耗的距离，consumed[1]为垂直方向应该消耗的距离
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }

    /**
     * 嵌套滚动时调用
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param child 使用此Behavior的AppBarLayout
     * @param target 发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     * @param dxConsumed 由目标View滚动操作消耗的水平像素数
     * @param dyConsumed 由目标View滚动操作消耗的垂直像素数
     * @param dxUnconsumed 由用户请求但是目标View滚动操作未消耗的水平像素数
     * @param dyUnconsumed 由用户请求但是目标View滚动操作未消耗的垂直像素数
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    }

    /**
     * 当嵌套滚动的子View准备快速滚动时调用
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param child 使用此Behavior的AppBarLayout
     * @param target 发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     * @param velocityX 水平方向的速度
     * @param velocityY 垂直方向的速度
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
     * @param child 使用此Behavior的AppBarLayout
     * @param target 发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     * @param velocityX 水平方向的速度
     * @param velocityY 垂直方向的速度
     * @param consumed 如果嵌套的子View消耗了快速滚动则为true
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
     * @param child 使用此Behavior的AppBarLayout
     * @param ev 手势事件
     */
    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev) {
        return super.onTouchEvent(parent, child, ev);
    }

    /**
     * 当触摸想要拦截时调用   关键所在 true 不拦截  false 拦截AppBarLayout的手势触摸
     *
     * @param parent 父布局CoordinatorLayout
     * @param child 使用此Behavior的AppBarLayout
     * @param ev 手势事件
     */
    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev) {
        return false;
    }

    /**
     * 当定制滚动时调用
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param abl 使用此Behavior的AppBarLayout
     * @param target 发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     */
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, abl, target, type);
    }
}
```

#### 4. 重写好AppBarLayout.Behavior类之后，在我们的项目中引用一下就好了；引用方法也很简单，在Strings.xml中添加
```xml <string name="behavior">com.stone.view.AppBarBehavior</string>```
#### 然后在布局中的AppBarLayout上进行引用
```xml 
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <com.amap.api.maps.MapView
        android:id="@+id/mapView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <android.support.design.widget.AppBarLayout
        android:id="@+id/appBar"
        android:layout_width="match_parent"
        android:layout_height="@dimen/scale_fourHundred_fifty"
        app:layout_behavior="@string/behavior"
        app:elevation="0dp">
     ...
```

#### 5. OK，引用好之后你会发现地图已经可以随意拖动了，到这里又有小伙伴发现怎么自己的FloatingActionButton(中间的圆圈)不能写文字呢？其实作者并不是直接用的android.support.design.widget.FloatingActionButton,而是利用TextView的style属性，看代码
```xml 
<TextView
        android:id="@+id/loadingTime"
        style="@style/Widget.Design.FloatingActionButton"
        android:layout_width="@dimen/scale_eighty"
        android:layout_height="@dimen/scale_eighty"
        android:gravity="center"
        android:textSize="@dimen/h1"
        android:elevation="5dp"
        android:textColor="@color/white"
        android:text="JR"
        app:layout_anchor="@id/nestScrollView"
        app:layout_anchorGravity="top|center" />
```
#### 用这个TextView替代布局中的android.support.design.widget.FloatingActionButton大事搞定。关于界面的美化各位自行发挥，完整代码直接下载就可运行。

## 赞赏

如果你喜欢我的分享，感觉这篇文章帮助到了你，可以点右上角 "Star" 支持一下 谢谢！ ^_^
你也还可以扫描下面的二维码~ 请作者喝一杯咖啡。

![](https://github.com/JR-Stone/img/blob/master/paycode/zfb.jpg) ![](https://github.com/JR-Stone/img/blob/master/paycode/wx.jpg)

> 如果希望捐赠之后能获得相关的帮助，可以选择加入下面的交流群，在群里可以直接和作者进行交流，与问题反馈。
如果在捐赠留言中备注名称，将会被记录到列表中~ 如果你也是github开源作者，捐赠时可以留下github项目地址或者个人主页地址，链接将会被添加到列表中起到互相推广的作用
[捐赠列表](https://github.com/JR-Stone/img/blob/master/payList.md)

![](https://github.com/JR-Stone/img/blob/master/qqflock/qq.png)
#### 进群须知
此群免费进入，大家可以相互讨论本库的相关使用和出现的问题，群主会很认真很努力的解决问题，但也不能保证能完美解决。

#### 友情链接
[github/faith-hb/WidgetCase](https://github.com/faith-hb/WidgetCase)  
[github/razerdp](https://github.com/razerdp)  
[github/SuperChenC/s-mvp](https://github.com/SuperChenC/s-mvp)  
[github/KingJA/LoadSir](https://github.com/KingJA/LoadSir)  
[github/jianshijiuyou](https://github.com/jianshijiuyou)    
[github/sugarya](https://github.com/sugarya)  
[github/stormzhang](https://github.com/stormzhang)
