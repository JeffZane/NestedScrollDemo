package com.demo.yzg.nestedscroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnHome = findViewById(R.id.btn_home);
        Button btnInbox = findViewById(R.id.btn_inbox);
        Button btnGroup = findViewById(R.id.btn_group);
        Button btnMe = findViewById(R.id.btn_me);

        btnHome.setOnClickListener(this);
        btnInbox.setOnClickListener(this);
        btnGroup.setOnClickListener(this);
        btnMe.setOnClickListener(this);

        btnHome.performClick();
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.btn_home:
                fragment = getSupportFragmentManager().findFragmentByTag(FragmentHome.class.getSimpleName());
                if (fragment == null) {
                    fragment = new FragmentHome();
                }
                break;
            case R.id.btn_inbox:
                fragment = getSupportFragmentManager().findFragmentByTag(FragmentInbox.class.getSimpleName());
                if (fragment == null) {
                    fragment = new FragmentInbox();
                }
                break;
            case R.id.btn_group:
                fragment = getSupportFragmentManager().findFragmentByTag(FragmentGroup.class.getSimpleName());
                if (fragment == null) {
                    fragment = new FragmentGroup();
                }
                break;
            case R.id.btn_me:
                fragment = getSupportFragmentManager().findFragmentByTag(FragmentMe.class.getSimpleName());
                if (fragment == null) {
                    fragment = new FragmentMe();
                }
                break;
        }

        if (fragment != null) {
            showFragment(fragment, 0, 0);
        }
    }

    protected synchronized void showFragment(Fragment to, int enterAnim, int exitAnim) {
        if (to == null) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(enterAnim, exitAnim);

        if (to.isAdded()) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment fragment : fragments) {
                if (to == fragment) {
                    transaction.show(fragment);
                } else {
                    transaction.hide(fragment);
                }
            }
        } else {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    transaction.hide(fragment);
                }
            }
            transaction.add(R.id.fl_content, to, to.getClass().getSimpleName());
        }
        //http://stackoverflow.com/questions/7469082/getting-exception-illegalstateexception-can-not-perform-this-action-after-onsa
        transaction.commitAllowingStateLoss();
    }
}
