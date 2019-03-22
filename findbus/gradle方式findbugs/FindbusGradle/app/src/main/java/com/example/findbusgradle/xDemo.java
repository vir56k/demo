package com.example.findbusgradle;

import android.util.Log;

public class xDemo {

    int run() {
        int i = 0;
        return 10/i;
    }


    void d222(){
        String idList = "hello,word";
        String[] idArray = idList.split(",");
        Object content = 3;
        if (isSuccess() == false) {
            logerror("发送站内信：mobileList=" + idArray + ",content=" + content);
        }
    }

    boolean isSuccess(){
        return false;
    }

    void logerror(String msg){
        Log.d("XX",msg);
    }
}
