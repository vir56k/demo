package vir56k.demoremovespace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            InputStream inputStream = getAssets().open("a.txt");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals(""))
                    continue;
                if (line.trim().startsWith("/"))
                    continue;
                if (line.trim().startsWith("*"))
                    continue;
                if (line.trim().startsWith("/*"))
                    continue;
                if (line.trim().startsWith("logger.debug"))
                    continue;
                if (line.trim().startsWith("logger.info"))
                    continue;
                if (line.trim().startsWith("logger.error"))
                    continue;
                if (line.trim().equals("{") || line.trim().equals("}"))
                {
                    sb.append(line);
                    continue;
                }
                sb.append(fileChinese(line));
                sb.append("\n");
            }

            String outRes = sb.toString();
            System.out.println(outRes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String fileChinese(String str){
        String reg = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(reg);
        Matcher mat=pat.matcher(str);

        String repickStr = mat.replaceAll("");
        System.out.println("去中文后:"+repickStr);
        return repickStr;
    }


}
