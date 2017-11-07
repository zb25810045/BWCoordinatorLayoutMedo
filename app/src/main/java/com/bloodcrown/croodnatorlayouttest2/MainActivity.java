package com.bloodcrown.croodnatorlayouttest2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goOn(View view) {

        int id = view.getId();
        Intent intent = new Intent(this, ScrollingActivity.class);

        if (id == R.id.buttom1) {
            startActivity(intent);
            return;
        }
        if (id == R.id.buttom2) {
            intent.setClass(this, FreeActivity1.class);
            startActivity(intent);
            return;
        }
        if (id == R.id.buttom3) {
            intent.setClass(this, TabLayoutActivity.class);
            startActivity(intent);
            return;
        }
        if (id == R.id.buttom4) {
            intent.setClass(this, BiliBiLiActivity.class);
            startActivity(intent);
            return;
        }
        if (id == R.id.buttom5) {
            intent.setClass(this, BottomSheetBehaviorActivity.class);
            startActivity(intent);
            return;
        }
        if (id == R.id.buttom6) {
            intent.setClass(this, BottomSheetDialogActivity.class);
            startActivity(intent);
            return;
        }
        if (id == R.id.buttom7) {
            intent.setClass(this, SwipeDismissBehaviorActivity.class);
            startActivity(intent);
            return;
        }
        if (id == R.id.buttom8) {
            intent.setClass(this, ZhiHuBehaviorActivity.class);
            startActivity(intent);
            return;
        }

    }

}
