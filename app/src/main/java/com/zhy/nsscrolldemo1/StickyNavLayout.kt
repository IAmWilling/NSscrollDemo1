package com.zhy.nsscrolldemo1

import android.R.attr
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.NestedScrollingParent2
import androidx.core.view.NestedScrollingParent3
import androidx.core.view.NestedScrollingParentHelper
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import java.lang.RuntimeException
import kotlin.math.log


class StickyNavLayout(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet),
    NestedScrollingParent2 {
    private val nestedScrollingParentHelper = NestedScrollingParentHelper(this)

    var nestedScrollChange: NestedScrollChange? = null
        set(value) {
            field = value
        }

    //content view
    private lateinit var mViewPager: ViewPager

    //header 高度
    private var mHeaderHeight = 0f

    //tab 高度
    private var mTabHeight = 0f

    //父控件高度
    private var mStickyNavLayoutHeight = 0f;

    override fun onFinishInflate() {
        super.onFinishInflate()
        mViewPager = findViewById(R.id.vp_viewpager)
        mHeaderHeight = resources.getDimension(R.dimen.header_dimen);
        mTabHeight = resources.getDimension(R.dimen.tab_dimen);
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mStickyNavLayoutHeight = h.toFloat()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var layoutParams = mViewPager.layoutParams
        layoutParams.height = (measuredHeight - mTabHeight).toInt()
        mViewPager.layoutParams = layoutParams
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        nestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes, type)
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean =
        axes == ViewCompat.SCROLL_AXIS_VERTICAL

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        //如果子view欲向上滑动，则先交给父view滑动
        val hideTop = dy > 0 && scrollY < mHeaderHeight
        //如果子view欲向下滑动，必须要子view不能向下滑动后，才能交给父view滑动
        val showTop =
            dy < 0 && scrollY >= 0 && !target.canScrollVertically(-1)
        if (hideTop || showTop) {
            scrollBy(0, dy)
            consumed[1] = dy // consumed[0] 水平消耗的距离，consumed[1] 垂直消耗的距离
        }

    }


    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        if (dyUnconsumed < 0) {//表示已经向下滑动到头，这里不用区分手势还是fling
            scrollBy(0, dyUnconsumed);
        }
    }

    override fun scrollTo(x: Int, y: Int) {
        var y1 = y;
        if (y1 < 0) {
            y1 = 0
        }
        if (y1 > mHeaderHeight) {
            y1 = mHeaderHeight.toInt()
        }
        var dest = y1 / mHeaderHeight

        nestedScrollChange?.let {
            it.onScroll(dest)
        } ?: throw RuntimeException("未能设置监听")
        if (scrollY != y1) super.scrollTo(x, y1)
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        nestedScrollingParentHelper.onStopNestedScroll(target, type)
    }

    override fun onNestedFling(
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean = false

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean = false

    interface NestedScrollChange {
        fun onScroll(dy: Float)
    }
}