package com.stone.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun behaviorIntent(view:View) {
        startActivity(Intent(this,BehaviorActivity::class.java))
    }
    fun toolBarIntent(view: View){
        startActivity(Intent(this,ToolBarDemoActivity::class.java))
    }
    fun transparentIntent(view: View){
        startActivity(Intent(this,TransparentActivity::class.java))
    }
}