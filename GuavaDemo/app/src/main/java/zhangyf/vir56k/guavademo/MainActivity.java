package zhangyf.vir56k.guavademo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyObject action = createMyObject();
        action = null;
        print(action);
        print2(action);
    }


    public void print2(@NonNull MyObject act) {
        checkNotNull(act);
        Log.i(TAG, "print2 " + act.name);
    }

    public void print(@Nullable MyObject act) {
        if (act != null)
            Log.i(TAG, "print " + act.name);
    }

    public static class MyObject {
        public String name;
    }

    private MyObject createMyObject() {
        return null;
    }
}
