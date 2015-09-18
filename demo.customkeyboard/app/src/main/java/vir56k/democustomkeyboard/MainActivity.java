package vir56k.democustomkeyboard;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText edittext1;
    PopupKeyboardUtil smallKeyboardUtil;
    private View viewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext1 = (EditText) findViewById(R.id.edittext1);

        smallKeyboardUtil = new PopupKeyboardUtil(self());
        smallKeyboardUtil.attachTo(edittext1, false);
        //smallKeyboardUtil.setAutoShowOnFocs(false);
    }

    public void onClickView(View view) {
        if (view.getId() == R.id.btn1)
            smallKeyboardUtil.showSoftKeyboard();
        if (view.getId() == R.id.btn2)
            smallKeyboardUtil.hideSoftKeyboard();

    }

    private Activity self() {
        return this;
    }
}
