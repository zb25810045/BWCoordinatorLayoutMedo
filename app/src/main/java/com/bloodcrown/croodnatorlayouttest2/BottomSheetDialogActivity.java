package com.bloodcrown.croodnatorlayouttest2;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class BottomSheetDialogActivity extends AppCompatActivity {

    View view_dialog;
    BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_dialog);
    }

    private View getDialogLayout() {
        if (view_dialog == null) {
            view_dialog = LayoutInflater.from(this).inflate(R.layout.layout_book_list2, null, false);
        }
        return view_dialog;
    }

    public void showDialog(View view) {

        if (!(view instanceof Button)) {
            return;
        }

        if (dialog == null) {
            dialog = new BottomSheetDialog(this);
            dialog.setContentView(R.layout.layout_book_list2);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
        }
//        BottomSheetDialog dialog = new BottomSheetDialog(this);
//        dialog.setContentView(R.layout.layout_book_list2);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.setCancelable(true);

        dialog.show();
    }

}
