package com.bloodcrown.croodnatorlayouttest2;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class SwipeDismissBehaviorActivity extends AppCompatActivity {

    View swipeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_dismiss_behavior);

        swipeView = findViewById(R.id.view1);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) swipeView.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.setBehavior(getSwipeDismissBehavior());
            swipeView.setLayoutParams(layoutParams);
        }
    }

    private SwipeDismissBehavior getSwipeDismissBehavior() {

        SwipeDismissBehavior behavior = new SwipeDismissBehavior();
        behavior.setDragDismissDistance(SwipeDismissBehavior.SWIPE_DIRECTION_ANY);
        behavior.setStartAlphaSwipeDistance(0f);
//        behavior.setSensitivity(0.5f);
        behavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
            @Override
            public void onDismiss(View view) {

            }

            @Override
            public void onDragStateChanged(int state) {

            }
        });
        return behavior;
    }


}
