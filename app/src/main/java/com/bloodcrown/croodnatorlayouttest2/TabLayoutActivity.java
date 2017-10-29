package com.bloodcrown.croodnatorlayouttest2;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class TabLayoutActivity extends AppCompatActivity {

    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        intiStateView();

        mTabLayout = (TabLayout) findViewById(R.id.view_tab);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab2"));

    }

    private void intiStateView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.flags = (layoutParams.flags | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
