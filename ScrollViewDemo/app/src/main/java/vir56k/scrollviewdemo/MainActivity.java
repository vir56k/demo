package vir56k.scrollviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    LinearLayout linerlayout1;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linerlayout1 = (LinearLayout) findViewById(R.id.linerlayout1);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        addChilds();
    }

    private void addChilds() {
        for (int i = 0; i < 10; i++) {
            View child = null;// createChild();
            child = getLayoutInflater().inflate(R.layout.item1, null);

            TextView textView = (TextView) child.findViewById(R.id.textView);
            textView.setText("item" + i);
            linerlayout1.addView(child);
        }
    }

//    private View createChild() {
//        return
//    }

}
