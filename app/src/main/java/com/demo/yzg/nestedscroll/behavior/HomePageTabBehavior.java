package com.demo.yzg.nestedscroll.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.demo.yzg.nestedscroll.R;
import com.demo.yzg.nestedscroll.helper.HeaderScrollingViewBehavior;

import java.util.List;

public class HomePageTabBehavior extends HeaderScrollingViewBehavior {

    public HomePageTabBehavior() {
    }

    public HomePageTabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void layoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        super.layoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return isDependOn(dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        offsetChildAsNeeded(child, dependency);
        return false;
    }

    private void offsetChildAsNeeded(View child, View dependency) {
        if (dependency.getTranslationY() == 0) {
            child.setTranslationY(0);
        } else {
            child.setTranslationY(dependency.getTranslationY());
        }
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

    private boolean isDependOn(View dependency) {
        return dependency != null && dependency.getId() == R.id.id_uc_news_header_pager;
    }
}
