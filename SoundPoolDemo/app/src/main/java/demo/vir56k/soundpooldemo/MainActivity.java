package demo.vir56k.soundpooldemo;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    SoundPool mSoundPool;
    private HashMap<Integer, Integer> soundPoolMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSoundPool = new SoundPool(1, AudioManager.STREAM_ALARM, 0);
        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                alert(" " + sampleId);
            }
        });
        soundPoolMap = new HashMap<Integer, Integer>();
        soundPoolMap.put(1, mSoundPool.load(this, R.raw.input_consignee_phone, 1));
        soundPoolMap.put(2, mSoundPool.load(this, R.raw.input_your_number, 1));
        soundPoolMap.put(3, mSoundPool.load(this, R.raw.qujianpwassowrd, 1));
        soundPoolMap.put(4, mSoundPool.load(this, R.raw.sao, 1));
    }

    private void alert(String s) {
        Log.i("XXXX", s + "");
    }


    public void onClickView(View view) {
        if (view.getId() == R.id.button) {
            mSoundPool.play(soundPoolMap.get(1), 1, 1, 0, 0, 1);
        }
        if (view.getId() == R.id.button3) {
            mSoundPool.play(soundPoolMap.get(2), 1, 1, 0, 0, 1);
        }
        if (view.getId() == R.id.button2) {
            mSoundPool.play(soundPoolMap.get(3), 1, 1, 0, 0, 1);
        }
    }

}
