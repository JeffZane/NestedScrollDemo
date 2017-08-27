package com.demo.yzg.nestedscroll.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.demo.yzg.nestedscroll.App;
import com.demo.yzg.nestedscroll.R;
import com.demo.yzg.nestedscroll.helper.HeaderScrollingViewBehavior;

import java.util.List;

public class HomePageViewPagerBehavior extends HeaderScrollingViewBehavior {

    public HomePageViewPagerBehavior() {
    }

    public HomePageViewPagerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void layoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        super.layoutChild(parent, child, layoutDirection);
        ViewCompat.offsetTopAndBottom(child, getTabHeight());
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return isDependOn(dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        offsetChildAsNeeded(parent, child, dependency);
        return false;
    }

    private void offsetChildAsNeeded(CoordinatorLayout parent, View child, View dependency) {
        child.setTranslationY(dependency.getTranslationY());
    }

    @Override
    protected View findFirstDependency(List<View> views) {
        for (int i = 0, z = views.size(); i < z; i++) {
            View view = views.get(i);
            if (isDependOn(view))
                return view;
        }
        return null;
    }

    @Override
    protected int getScrollRange(View v) {
        if (isDependOn(v)) {
            return Math.max(0, v.getMeasuredHeight() - getTabHeight());
        } else {
            return super.getScrollRange(v);
        }
    }

    private int getTabHeight() {
        return App.getAppContext().getResources().getDimensionPixelOffset(R.dimen.home_page_tab_layout_height);
    }

    private boolean isDependOn(View dependency) {
        return dependency != null && dependency.getId() == R.id.id_uc_news_header_pager;
    }
}
