package vir56k.democustomkeyboard;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.lang.reflect.Method;

public class PopupKeyboardUtil {
    private Activity mActivity;

    private KeyboardView keyboardView;
    private Keyboard keyboard;// 全键盘包括数字和字母

    private EditText editText1;

    public PopupKeyboardUtil(Activity mActivity) {
        this.mActivity = mActivity;
        this.keyboard = new Keyboard(mActivity, R.xml.small_keyboard);
    }

    public void attachTo(EditText editText, boolean isAuto) {
        this.editText1 = editText;
        hideSystemSofeKeyboard(this.editText1);
        setAutoShowOnFocs(isAuto);
    }

    public void setAutoShowOnFocs(boolean enable) {
        if (editText1 == null)
            return;
        if (enable)
            editText1.setOnFocusChangeListener(onFocusChangeListener1);
        else
            editText1.setOnFocusChangeListener(null);
    }

    View.OnFocusChangeListener onFocusChangeListener1 = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus)
                showSoftKeyboard();
            else
                hideSoftKeyboard();
        }
    };

    View viewContainer;

    public void showSoftKeyboard() {

        if (viewContainer == null) {
            viewContainer = mActivity.getLayoutInflater().inflate(R.layout.keyboardview_layout, null);
        } else {
            if (viewContainer.getParent() != null)
                return;
        }

        FrameLayout frameLayout = (FrameLayout) mActivity.getWindow().getDecorView();
        KeyboardView keyboardView = (KeyboardView) viewContainer.findViewById(R.id.keyboard_view);
        this.keyboardView = keyboardView;
        this.keyboardView.setKeyboard(keyboard);
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(false);
        this.keyboardView.setOnKeyboardActionListener(listener2);

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.BOTTOM;
        frameLayout.addView(viewContainer, lp);
        //viewContainer.setVisibility(View.GONE);
        viewContainer.setAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.down_to_up));
    }


    public void hideSoftKeyboard() {
        if (viewContainer != null && viewContainer.getParent() != null) {
            ((ViewGroup) viewContainer.getParent()).removeView(viewContainer);
        }
    }

    public boolean isShowing() {
        if (viewContainer == null)
            return false;
        return viewContainer.getVisibility() == View.VISIBLE;
    }

    /**
     * 隐藏系统键盘
     *
     * @param editText
     */
    public static void hideSystemSofeKeyboard(EditText editText) {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 11) {
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);

            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            editText.setInputType(InputType.TYPE_NULL);
        }
    }

    private OnKeyboardActionListener listener2 = new OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            if (editText1 != null) {
                keyCode_delect(primaryCode, editText1);
            }
            keyboardView.postInvalidate();
        }
    };

    /**
     * 判断回退键 和大小写切换
     *
     * @param primaryCode
     * @param edText
     */
    private void keyCode_delect(int primaryCode, EditText edText) {

        Editable editable = edText.getText();
        int start = edText.getSelectionStart();
        if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
            if (edText.hasFocus()) {
                if (!TextUtils.isEmpty(editable)) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
            }

        } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {// 大小写切换
            keyboardView.setKeyboard(keyboard);
        } else {
            if (edText.hasFocus()) {
                editable.insert(start, Character.toString((char) primaryCode));
            }
        }
    }

}
