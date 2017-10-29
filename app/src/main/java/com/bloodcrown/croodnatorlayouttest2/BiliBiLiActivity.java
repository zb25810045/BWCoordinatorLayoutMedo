package com.bloodcrown.croodnatorlayouttest2;

import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class BiliBiLiActivity extends AppCompatActivity {

    // appbar展开状态
    public static final int STAET_EXPANDED = 1;
    // appbar折叠状态
    public static final int STAET_COLLAPSED = -1;
    // appbar中间状态
    public static final int STAET_ING = 0;

    int state = 2;

    // appbar 响应滚动状态
    public static final int SCROLL_ING = -1;
    // appbar 不响应滚动状态
    public static final int SCROLL_NO = 0;

    int state_scroll = SCROLL_ING;


    View appBar_show;
    View appBar_hint;
    FloatingActionButton mFAB;
    AppBarLayout mAppBar;
    NestedScrollView mScrollView;
    View mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bili_bi_li);

        intiStateView();

        appBar_show = findViewById(R.id.appbar_show);
        appBar_hint = findViewById(R.id.appbar_hint);
        mFAB = (FloatingActionButton) findViewById(R.id.view_fab);
        mAppBar = (AppBarLayout) findViewById(R.id.app_bar);
        mScrollView = (NestedScrollView) findViewById(R.id.view_nest);
        mPlayButton = findViewById(R.id.appbar_hint_name);

        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

//                int totalScrollRange = appBarLayout.getTotalScrollRange();
//                Log.d("AAA", "total:" + totalScrollRange + "/Offset:" + verticalOffset);

                verticalOffset = Math.abs(verticalOffset);

                // 初始展开状态
                if (verticalOffset == 0) {
                    if (state != STAET_EXPANDED) {
                        // 是展开状态，重置标记为展开，显示 appbar 布局控件
                        state = STAET_EXPANDED;
                        appBar_show.setVisibility(View.VISIBLE);
                        appBar_hint.setVisibility(View.GONE);
                    }
                    return;
                }

                // 折叠状态
                if (verticalOffset >= appBarLayout.getTotalScrollRange()) {
                    if (state != STAET_COLLAPSED) {
                        // 是折叠状态，重置标记为折叠，显示 appbar 布局控件
                        state = STAET_COLLAPSED;
                        appBar_hint.setVisibility(View.VISIBLE);
                        appBar_show.setVisibility(View.GONE);
                    }
                    return;
                }

                // 中间状态
                if (state != STAET_ING) {
                    // 是中间状态，重置标记为ing，显示 appbar 布局控件
                    state = STAET_ING;
                    appBar_show.setVisibility(View.VISIBLE);
                    appBar_hint.setVisibility(View.GONE);
                }
            }
        });

        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == STAET_EXPANDED) {
                    if (state_scroll == SCROLL_ING) {
                        // 取消滚动控件的嵌套滚动
                        mScrollView.setNestedScrollingEnabled(false);
                        state_scroll = SCROLL_NO;
                    } else if (state_scroll == SCROLL_NO) {
                        mScrollView.setNestedScrollingEnabled(true);
                        state_scroll = SCROLL_ING;
                    }
                }
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == STAET_COLLAPSED) {
                    // 处于折叠状态时才能玩
                    mAppBar.setExpanded(true);
                    state = STAET_EXPANDED;
                }
            }
        });

    }

    private void intiStateView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.flags = (layoutParams.flags | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
