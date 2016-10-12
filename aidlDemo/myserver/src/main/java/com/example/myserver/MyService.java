package com.example.myserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyunfei on 16/10/12.
 */
public class MyService extends Service {
    public static final String TAG = "MyService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, String.format("on bind,intent = %s", intent.toString()));
        return binder;
    }


    private final IRemoteService.Stub binder = new IRemoteService.Stub() {
        public static final String TAG = "IRemoteService.Stub";
        private List<Entity> data = new ArrayList<Entity>();

        @Override
        public void doSomeThing(int anInt, String aString) throws RemoteException {
            Log.d(TAG, String.format("收到：%s, %s", anInt, aString));
        }

        @Override
        public void addEntity(Entity entity) throws RemoteException {
            Log.d(TAG, String.format("收到：entity = %s", entity));
            data.add(entity);
        }

        @Override
        public List<Entity> getEntity() throws RemoteException {
            return data;
        }

        @Override
        public void asyncCallSomeone(String para, IMyCallback callback) throws RemoteException {
            RemoteCallbackList<IMyCallback> remoteCallbackList = new RemoteCallbackList<>();
            remoteCallbackList.register(callback);
            final int len = remoteCallbackList.beginBroadcast();
            for (int i = 0; i < len; i++) {
                remoteCallbackList.getBroadcastItem(i).onSuccess(para + "_callbck");
            }
            remoteCallbackList.finishBroadcast();
        }
    };
}
