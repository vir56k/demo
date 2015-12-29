package vir56k.autotextviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    AutoCompleteTextView autotext;
    private ArrayAdapter<String> arrayAdapter;
    List<String> lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autotext = (AutoCompleteTextView) findViewById(R.id.autotext);
//        String [] arr={"aa","aab","aac"};
        lst = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lst);
        autotext.setAdapter(arrayAdapter);
    }

    public void onStart() {
        autotext.addTextChangedListener(mTextWatcher);
        super.onStart();
    }


    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 1) {
                lst.clear();
                for (int i = 0; i < s.length(); i++) {
                    lst.add(s.toString() + i);
                }
                arrayAdapter.clear();
                arrayAdapter.addAll(lst);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void onStop() {
        autotext.removeTextChangedListener(mTextWatcher);
        super.onStop();
    }
}
