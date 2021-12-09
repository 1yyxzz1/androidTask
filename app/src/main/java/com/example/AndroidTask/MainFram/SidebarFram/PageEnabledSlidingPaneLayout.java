package com.example.AndroidTask.MainFram.SidebarFram;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.core.view.MotionEventCompat;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import com.example.AndroidTask.Database.MyApplication;


public class PageEnabledSlidingPaneLayout extends SlidingPaneLayout {
    private float mInitialMotionX;
    private float mInitialMotionY;
    private float mEdgeSlop;//手滑动的距离

    public PageEnabledSlidingPaneLayout(Context context) {
        this(context, null);
    }

    public PageEnabledSlidingPaneLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageEnabledSlidingPaneLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);


        ViewConfiguration config = ViewConfiguration.get(context);
        mEdgeSlop = config.getScaledEdgeSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (MotionEventCompat.getActionMasked(ev)) {
            case MotionEvent.ACTION_DOWN: {
                // 屏幕检测到第一个触点按下
                mInitialMotionX = ev.getX();
                mInitialMotionY = ev.getY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                // 触点在屏幕上移动
                final float x = ev.getX();
                final float y = ev.getY();

                // 避免与其他划动控件冲突
                if (mInitialMotionX > mEdgeSlop && !isOpen() && canScroll(this, false,
                        Math.round(x - mInitialMotionX), Math.round(x), Math.round(y))) {

                    MotionEvent cancelEvent = MotionEvent.obtain(ev);
                    cancelEvent.setAction(MotionEvent.ACTION_CANCEL);
                    return super.onInterceptTouchEvent(cancelEvent);
                }
            }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
