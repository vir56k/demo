package vir56k.autotextviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = (ListView) findViewById(R.id.listview1);
        View headView = getLayoutInflater().inflate(R.layout.activity_main2_head, null);
        listView.addHeaderView(headView);
        ArrayList<String> lst = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            lst.add(" " + i);
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lst);
        listView.setAdapter(arrayAdapter);
    }


}
