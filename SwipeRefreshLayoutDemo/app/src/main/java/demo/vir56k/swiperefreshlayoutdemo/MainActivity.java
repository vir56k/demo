package demo.vir56k.swiperefreshlayoutdemo;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REFRESH_COMPLETE = 0X110;
    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listview1);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout1);

        mDatas = creatDataSource();
        mSwipeLayout.setOnRefreshListener(mSwipeRefreshLayoutOnRefreshListener);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        mListView.setAdapter(mAdapter);
    }

    private List<String> creatDataSource() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            mDatas.add("item" + i);
        }
        return mDatas;
    }


    SwipeRefreshLayout.OnRefreshListener mSwipeRefreshLayoutOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
        }
    };

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    List<String> lst = new ArrayList<String>();
                    for (int i = mDatas.size() + 3; i > mDatas.size(); i--) {
                        lst.add("item -" + i);
                    }
                    mDatas.addAll(0, lst);
                    mAdapter.notifyDataSetChanged();
                    mSwipeLayout.setRefreshing(false);
                    break;

            }
        }

        ;
    };
}
