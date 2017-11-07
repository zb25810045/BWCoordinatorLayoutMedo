package behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zbzbgo on 2017/11/7.
 */

public class ButtomNavigationBehavior extends CoordinatorLayout.Behavior {

    int offsetY = 0;

    AnimatorUtils animatorUtils;

    // 这个构造方法必须重写，CoordinatorLayout 会使用这个构造方法 new 一个直接子 view 的 Behavior 对象
    public ButtomNavigationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        animatorUtils = new AnimatorUtils();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {

        // 我们只需要在产生垂直方向的滚动时进行乔套滚动
        if (nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL) {
            // 获取控件左上角到父控件底部的具体
            offsetY = coordinatorLayout.getMeasuredHeight() - child.getTop();
            return true;
        }
        return false;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {

        // dy>0 ，表示上滑，查看更多内容，上下导航栏要隐藏
        if (dy > 0) {
            animatorUtils.startHindAnimator(child, offsetY);
            return;
        }

        // dy<0 ，表示下滑，查看前面内容，上下导航栏要显示
        if (dy < 0) {
            animatorUtils.startShowAnimator(child, offsetY);
            return;
        }

    }

    public class AnimatorUtils {

        public boolean isAnimator = false;
        public boolean isShow = true;
        public boolean isHind = false;
        public int offsetY = 0;

        public void startHindAnimator(View view, int offsetY) {

            if (isAnimator || isHind) {
                return;
            }

            ViewPropertyAnimatorCompat hindPropertyAnimatorCompat = ViewCompat.animate(view).translationY(offsetY).setListener(new ViewPropertyAnimatorListener() {
                @Override
                public void onAnimationStart(View view) {
                    isAnimator = true;
                }

                @Override
                public void onAnimationEnd(View view) {
                    isAnimator = false;
                    isHind = true;
                    isShow = false;
                }

                @Override
                public void onAnimationCancel(View view) {
                    isAnimator = false;
                }
            }).setDuration(200);
            hindPropertyAnimatorCompat.start();

        }

        public void startShowAnimator(View view, int offsetY) {
            if (isAnimator || isShow) {
                return;
            }

            ViewPropertyAnimatorCompat showPropertyAnimatorCompat = ViewCompat.animate(view).translationY(0).setListener(new ViewPropertyAnimatorListener() {
                @Override
                public void onAnimationStart(View view) {
                    isAnimator = true;
                }

                @Override
                public void onAnimationEnd(View view) {
                    isAnimator = false;
                    isHind = false;
                    isShow = true;
                }

                @Override
                public void onAnimationCancel(View view) {
                    isAnimator = false;
                }
            }).setDuration(200);
            showPropertyAnimatorCompat.start();
        }
    }
}
