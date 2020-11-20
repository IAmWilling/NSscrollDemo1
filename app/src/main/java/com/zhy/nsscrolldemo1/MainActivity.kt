package com.zhy.nsscrolldemo1

import android.animation.ArgbEvaluator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var list = arrayListOf<Fragment>(
            FragmentDemo1(),
            FragmentDemo1(),
            FragmentDemo1(),
            FragmentDemo1()
        )
        vp_viewpager.adapter = VpAdapter(list, supportFragmentManager)
        tl_tab.setupWithViewPager(vp_viewpager)
        sticknav.nestedScrollChange = object : StickyNavLayout.NestedScrollChange {
            override fun onScroll(dy: Float) {
                var argbEvaluator = ArgbEvaluator()
                var color = argbEvaluator.evaluate(
                    dy,
                    Color.TRANSPARENT,
                    resources.getColor(R.color.colorPrimary)
                ) as Int
                fm.setBackgroundColor(color)
//                tl_tab.setBackgroundColor(color)
            }
        }
    }
}