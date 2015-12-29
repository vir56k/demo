package vir56k.jodatimedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DateTime dt1 = new DateTime(2000, 1, 1, 0, 0);
        DateTime dt2 = new DateTime(2000, 1, 1, 1, 30);
        Minutes.minutesBetween(dt1, dt2).getMinutes();

        test1(dt1,dt2);
    }

    public static void test1(DateTime dt1, DateTime dt2) {

        // 毫秒ms
        long diff = dt2.getMillis() - dt1.getMillis();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        System.out.print("时间相差：");
        System.out.print(diffDays + " 天 ");
        System.out.print(diffHours + " 小时 ");
        System.out.print(diffMinutes + " 分钟 ");
        System.out.print(diffSeconds + " 秒.");
        System.out.println();


        System.out.print(Days.daysBetween(dt1, dt2).getDays() + " 天 ");
        System.out.print(Hours.hoursBetween(dt1, dt2).getHours()  + " 小时 ");
        System.out.print(Minutes.minutesBetween(dt1, dt2).getMinutes()   + " 分钟 ");
        System.out.print(Seconds.secondsBetween(dt1, dt2).getSeconds() + " 秒.");
        System.out.println();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
