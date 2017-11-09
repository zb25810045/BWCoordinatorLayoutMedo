package behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.bloodcrown.croodnatorlayouttest2.R;

/**
 * Created by zbzbgo on 2017/11/8.
 */

public class KaiYanAppBarBehavior extends CoordinatorLayout.Behavior {

    private Context mContext;
    private View mViewHead;
    private int appbarHeight = 0;

    public KaiYanAppBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {

        mViewHead = parent.findViewById(R.id.kaiyan_appbar);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (layoutParams != null && layoutParams.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
            child.layout(0, 0, layoutParams.width, layoutParams.height);
            child.setTranslationY(getAppBarHeight());
        }

        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {

        // 在嵌套滚动开始之前，我们在这里进行上滑处理，先于滚动消费掉滚动数据，这里就是指我们上移列表，这样我们上移列表了，列表就不会再滚动了
        float translationY = child.getTranslationY();
        float offsetaAlphe = 0;

        if (dy > 0 && translationY > 0) {

            // 进行列表的位移
            float offestY = translationY - dy;
            Log.d("AAA", "translationY:" + translationY + " / dy:" + dy);

            if (offestY < 0) {
                // 说明此时Y轴线的偏移量不足本次一帧时间内的滑动量，所以需要特别处理一下
                Log.d("AAA", "AAA");
                offestY = 0;
                consumed[1] = (int) translationY;
            } else {
                Log.d("AAA", "BBB:");
                offestY = translationY - dy;
                consumed[1] = dy;
            }
            Log.d("AAA", "translationY:" + offestY);
            child.setTranslationY(offestY);

            // 进行头部的透明度变化
            if (mViewHead != null) {
                offsetaAlphe = 0.5f + 0.5f * (translationY / getAppBarHeight());
                mViewHead.setAlpha(offsetaAlphe);
            }

        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        int appBarHeight = getAppBarHeight();
        float offestY = 0;
        float translationY = child.getTranslationY();
        float offsetaAlphe = 0;

        if (dyUnconsumed < 0 && translationY < appBarHeight) {

            // 进行列表的位移
            offestY = translationY + Math.abs(dyUnconsumed);
            if (offestY < 0) {
                offestY = 0;
            }
            if (offestY >= appBarHeight) {
                offestY = appBarHeight;
            }
            child.setTranslationY(offestY);

            // 进行头部的透明度变化
            if (mViewHead != null) {
                if (translationY > appBarHeight) {
                    translationY = appBarHeight;
                }
                offsetaAlphe = 0.5f + 0.5f * (translationY / getAppBarHeight());
                mViewHead.setAlpha(offsetaAlphe);
            }
        }
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        Log.d("AAA", "onNestedPreFling:" + velocityY);
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    private int getAppBarHeight() {

        if (appbarHeight == 0) {
            if (mViewHead == null) {
                return appbarHeight;
            }
            appbarHeight = mViewHead.getMeasuredHeight();
            if (appbarHeight == 0) {
                mViewHead.measure(0, 0);
                appbarHeight = mViewHead.getMeasuredHeight();
            }
        }
        return appbarHeight;
//        return mContext.getResources().getDimensionPixelOffset(R.dimen.app_bar_height);
    }
}
