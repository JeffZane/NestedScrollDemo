
package com.demo.yzg.nestedscroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.demo.yzg.nestedscroll.behavior.HomePageMenuLayoutBehavior;

public class FragmentHome extends Fragment implements HomePageMenuLayoutBehavior.OnPagerStateListener {
    private ImageView backToTop;
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private HomePageMenuLayoutBehavior mPagerBehavior;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
    }

    private void initView(View view) {
        LinearLayout llMenu = view.findViewById(R.id.id_uc_news_header_pager);
        ImageView ivExpand = view.findViewById(R.id.iv_expand);
        tabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.view_pager);


        mPagerBehavior = (HomePageMenuLayoutBehavior) ((CoordinatorLayout.LayoutParams) llMenu.getLayoutParams()).getBehavior();
        if (mPagerBehavior != null) {
            mPagerBehavior.setPagerStateListener(this);
        }

        ivExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPagerBehavior != null && mPagerBehavior.isClosed()) {
                    mPagerBehavior.openPager();
                }
            }
        });

        mViewPager.setAdapter(new PagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onPagerClosed() {
//        Snackbar.make(mViewPager, "pager closed", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onPagerOpened() {
//        Snackbar.make(mViewPager, "pager opened", Snackbar.LENGTH_SHORT).show();
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return TestFragment.newInstance(i);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Top Reads";
            } else {
                return "Video News";
            }
        }
    }
}
