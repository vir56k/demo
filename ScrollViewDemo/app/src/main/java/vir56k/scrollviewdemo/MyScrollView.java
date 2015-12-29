package vir56k.scrollviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;

/**
 * Created by zhangyunfei on 15/12/21.
 */
public class MyScrollView extends ScrollView {
    private static final String TAG = MyScrollView.class.getSimpleName();
    GestureDetector mGesture = null;

    public MyScrollView(Context context) {
        super(context);
        init();
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mGesture = new GestureDetector(getContext(), mOnGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mGesture.onTouchEvent(ev);
        return super.onTouchEvent(ev);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);


        int range = computeVerticalScrollRange();
        printScreenInfo();
        printSelfInfo();
        Log.d(TAG, "RANGE: " + range);

        ViewGroup child = (ViewGroup) getChildAt(0);
        Log.d(TAG, String.format("内容元素的宽高: %s x %s", child.getMeasuredWidth(), child.getMeasuredHeight()));
        View child_child = child.getChildAt(0);
        Log.d(TAG, String.format("内容元素的 子元素的 宽高: %s x %s", child_child.getMeasuredWidth(), child_child.getMeasuredHeight()));


        Log.d(TAG, String.format("l=%s; t=%s; oldl=%s; oldt=%s", l, t, oldl, oldt));
    }

    private void printSelfInfo() {
        Log.d(TAG, String.format("自己的宽高： %s x %s", getWidth(), getHeight()));
    }

    private void printScreenInfo() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Log.d(TAG, String.format("屏幕的宽高： %s x %s", width, height));

    }

    private void doOnBorderListener() {
//        View contentView = getChildAt(0);
//        contentView.getMeasuredHeight() <= getScrollY() + getHeight();
//
//        if (contentView != null && contentView.getMeasuredHeight() <= getScrollY() + getHeight()) {
//            if (onBorderListener != null) {
//                onBorderListener.onBottom();
//            }
//        } else if (getScrollY() == 0) {
//            if (onBorderListener != null) {
//                onBorderListener.onTop();
//            }
//        }
    }


    GestureDetector.OnGestureListener mOnGestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if ((e1.getY() - e2.getY()) > 20 && Math.abs(velocityY) > 0) {
                Log.d(TAG, "fling......");
            }
            return false;
        }
    };
}
