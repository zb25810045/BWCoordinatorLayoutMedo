package com.bloodcrown.croodnatorlayouttest2;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BottomSheetBehaviorActivity extends AppCompatActivity {

    private View view_bottom;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_behavior);
        view_bottom = findViewById(R.id.view_bottom);
        mBottomSheetBehavior = BottomSheetBehavior.from(view_bottom);
    }

    public void switchState(View view) {

        if (!(view instanceof Button) || mBottomSheetBehavior == null) {
            return;
        }

        Button button = (Button) view;
        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            button.setText("展开显示");
            return;
        }

        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            button.setText("折叠收起");
            return;
        }
    }

}
