package com.stone.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.stone.view.databinding.ActivityTransparentBinding
import kotlin.math.abs

class TransparentActivity : AppCompatActivity() {

    lateinit var baseBinding: ActivityTransparentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transparent)
        initView()
        showImg()//显示图片
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        //绑定视图
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_transparent)
        //透明状态栏
        StatusBarUtils.setImmersionStateMode(this)
        //滑动偏移监听事件
        baseBinding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val toolbarHeight = appBarLayout.totalScrollRange
            val dy = abs(verticalOffset)
            if (dy <= toolbarHeight) {
                val than = dy.toFloat() / toolbarHeight
                val alpha = than * 255
                //文字颜色透明度
                baseBinding.title.setTextColor(Color.argb(alpha.toInt(), 0, 0, 0))
                //背景透明度
                baseBinding.toolBarLl.setBackgroundColor(Color.argb(alpha.toInt(), 255, 255, 255))
            }
        })

        baseBinding.title.setOnClickListener{
            finish()
        }
    }

    /**
     * 使用Glide加载显示网络图片 记得加网络权限和http地址url访问许可
     */
    private fun showImg() {
        Glide.with(this)
                .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdingyue.nosdn.127.net%2FtFpW5np6B29BdKoNqVrXngVtDBXrRDWkjTpo6dC31fdTk1556418127777compressflag.jpeg&refer=http%3A%2F%2Fdingyue.nosdn.127.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1613799018&t=06901b31235a677ea90a1e893c870880")
                .into(baseBinding.one)
        Glide.with(this)
                .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Ff603918fa0ec08fa6443cb2657ee3d6d54fbdaf4.jpg&refer=http%3A%2F%2Fhiphotos.baidu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1613799018&t=73365e7c6796f84f339c8d128b51fb14")
                .into(baseBinding.two)
        Glide.with(this)
                .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic2.zhimg.com%2F50%2Fv2-79d594594fdd7441e848d3da749751de_hd.jpg&refer=http%3A%2F%2Fpic2.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1613799018&t=dd9c98afb4644677a9d51ed1204ea51a")
                .into(baseBinding.three)
        Glide.with(this)
                .load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage11.m1905.cn%2Fuploadfile%2F2009%2F0910%2F195%2F20090910034058548.jpg&refer=http%3A%2F%2Fimage11.m1905.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1613799018&t=276c48498abbcacc362917717bbbd38c")
                .into(baseBinding.four)
        Glide.with(this)
                .load("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/dcc451da81cb39db0b2d4545d6160924ab183037.jpg")
                .into(baseBinding.five)
    }
}