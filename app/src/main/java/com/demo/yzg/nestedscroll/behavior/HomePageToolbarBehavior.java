package com.demo.yzg.nestedscroll.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.demo.yzg.nestedscroll.App;
import com.demo.yzg.nestedscroll.R;

public class HomePageToolbarBehavior extends CoordinatorLayout.Behavior<View> {

    public HomePageToolbarBehavior() {
    }

    public HomePageToolbarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        offsetChildAsNeeded(child, dependency);
        return false;
    }

    private void offsetChildAsNeeded(View child, View dependency) {
        float transY = dependency.getTranslationY();
        int dependencyHeight = dependency.getHeight();
        if (transY <= (-dependencyHeight - getTitleHeight())) {
            child.setTranslationY(-getTitleHeight());
        } else if (transY < (-dependencyHeight)) {
            child.setTranslationY(transY + dependencyHeight);
        } else if (transY >= (-dependencyHeight)) {
            child.setTranslationY(0);
        }
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return isDependOn(dependency);
    }

    private int getTitleHeight() {
        return App.getAppContext().getResources().getDimensionPixelOffset(R.dimen.home_page_toolbar_height);
    }

    private boolean isDependOn(View dependency) {
        return dependency != null && dependency.getId() == R.id.id_uc_news_header_pager;
    }
}
