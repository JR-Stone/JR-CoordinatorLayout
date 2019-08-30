# JR-CoordinatorLayout
CoordinatorLayout + AppBarLayout + NestedScrollView 组合使用实现地图背景，滑动悬停华丽效果。仿饿了么地图界面。TextView 实现FloatingActionButton效果

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Arsenal](https://img.shields.io/badge/Arsenal%20-%20SmartRefresh-4cae4c.svg)](https://android-arsenal.com/details/1/6001)
[![JCenter](https://img.shields.io/badge/%20JCenter%20-1.1.0-5bc0de.svg)](https://bintray.com/scwang90/maven/SmartRefreshLayout/_latestVersion)
[![MinSdk](https://img.shields.io/badge/%20MinSdk%20-%2012%2B%20-f0ad4e.svg)](https://android-arsenal.com/api?level=12)
[![Methods](https://img.shields.io/badge/Methods%20%7C%20Size%20-%20784%20%7C%20121%20KB-d9534f.svg)](http://www.methodscount.com/?lib=com.scwang.smartrefresh%3ASmartRefreshLayout%3A1.0.4)

<!-- [![Platform](https://img.shields.io/badge/Platform-Android-f0ad4e.svg)](https://www.android.com) -->
<!-- [![Author](https://img.shields.io/badge/Author-scwang90-11bbff.svg)](https://github.com/scwang90) -->

## [English](https://github.com/scwang90/SmartRefreshLayout/blob/master/README_EN.md) | 中文

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

<!--## 每天领红包 -->

<!--最近开通了支付宝商家，生成了个红包二维码，经常用支付宝的童鞋可有扫码领优惠红包，扫码只会拿红包，不会有任何损失，每天都可以扫码哦！-->

<!--![支付宝红包](https://github.com/scwang90/MultiWaveHeader/blob/master/art/pay_alipay_red_packet.png?raw=true)-->

<!--你也可以在支付宝中直接搜索 **553866294** 来获取红包。如果得到的是花呗红包，也不用失望。如果你经常使用信用卡的话那么使用花呗红包非常适合你，它也和信用卡一样先消费后还款，关键是每天都能扫红包省钱！-->

<!--传送门

 - [属性文档](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_property.md)
 - [常见问题](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_faq.md)
 - [智能之处](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_smart.md)
 - [更新日志](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_update.md)
 - [博客文章](https://segmentfault.com/a/1190000010066071)
 - [源码下载](https://github.com/scwang90/SmartRefreshLayout/releases)
 - [多点触摸](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_multitouch.md)
 - [自定义Header](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_custom.md)-->

## Demo
[下载 APK-Demo](https://github.com/JR-Stone/img/blob/master/apk/app-coor.apk)

## 简单使用
人生的第一次写文章呀！还不知道我这第一次会被谁给夺走...还请多多指教，手下留情，少喷少喷！废话不多说，咱们言归正传，其实这三个控件组合可以制作出很多炫酷的界面，不过今天主要分享一下以地图为背景手动滑动透明AppBar渐变的效果，和之前的老版（饿了么）APP运输中订单详情界面一样；先上代码和基本效果图

#### layout布局activity_main.xml
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
        android:elevation="2dp"
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

    <TextView
        android:id="@+id/loadingTime"
        style="@style/Widget.Design.FloatingActionButton"
        android:layout_width="@dimen/scale_eighty"
        android:layout_height="@dimen/scale_eighty"
        android:gravity="center"
        android:textSize="@dimen/h1"
        android:elevation="2dp"
        android:textColor="@color/white"
        android:text="JR"
        app:layout_anchor="@id/nestScrollView"
        app:layout_anchorGravity="top|center" />
</android.support.design.widget.CoordinatorLayout>
```
#### java代码 MianActivity.java
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
#### 导入包
```
/*高德地图*/
implementation 'com.amap.api:3dmap:latest.integration'
/*注释*/
implementation 'com.jakewharton:butterknife:8.7.0'
annotationProcessor 'com.jakewharton:butterknife-compiler:8.7.0'
```
|默认|滑动中|置顶|
|:---:|:---:|:---:|
|![](https://github.com/JR-Stone/img/blob/master/coordinator/jr_view.png)|![](https://github.com/JR-Stone/img/blob/master/coordinator/jr_view1.png)|![](https://github.com/JR-Stone/img/blob/master/coordinator/jr_view2.png)|

## 赞赏

如果你喜欢我的分享，感觉这篇文章帮助到了你，可以点右上角 "Star" 支持一下 谢谢！ ^_^
你也还可以扫描下面的二维码~ 请作者喝一杯咖啡。

![](https://github.com/JR-Stone/img/blob/master/paycode/zfb.jpg) ![](https://github.com/JR-Stone/img/blob/master/paycode/wx.jpg)

> 如果希望捐赠之后能获得相关的帮助，可以选择加入下面的交流群，在群里可以直接和作者进行交流，与问题反馈。
如果在捐赠留言中备注名称，将会被记录到列表中~ 如果你也是github开源作者，捐赠时可以留下github项目地址或者个人主页地址，链接将会被添加到列表中起到互相推广的作用
[捐赠列表](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_donationlist.md)

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
