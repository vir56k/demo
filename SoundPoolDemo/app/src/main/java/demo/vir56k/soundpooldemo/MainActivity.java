package demo.vir56k.soundpooldemo;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public static final int KEY_SOUND_A1 = 1;
    public static final int KEY_SOUND_A2 = 1;

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
        soundPoolMap.put(KEY_SOUND_A1, mSoundPool.load(this, R.raw.a1, 1));
        soundPoolMap.put(KEY_SOUND_A2, mSoundPool.load(this, R.raw.a2, 1));
    }

    private void alert(String s) {
        Log.i("Alert: ", s + "");
    }

    public void onClickView(View view) {
        if (view.getId() == R.id.button) {
            mSoundPool.play(soundPoolMap.get(KEY_SOUND_A1), 1, 1, 0, 0, 1);
        }
        if (view.getId() == R.id.button2) {
            mSoundPool.play(soundPoolMap.get(KEY_SOUND_A2), 1, 1, 0, 0, 1);
        }
    }

}
