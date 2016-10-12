package com.example.zhangyunfei.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myserver.Entity;
import com.example.myserver.IMyCallback;
import com.example.myserver.IRemoteService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean mBound = false;
    private IRemoteService iRemoteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBound) {
                    alert("未连接到远程服务");
                    return;
                }
                try {
                    Entity entity = new Entity(1, "zhang");
                    if (iRemoteService != null)
                        iRemoteService.addEntity(entity);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }


        });

        findViewById(R.id.btnList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBound) {
                    alert("未连接到远程服务");
                    return;
                }
                if (iRemoteService != null) {
                    try {
                        List<Entity> entityList = iRemoteService.getEntity();

                        StringBuilder sb = new StringBuilder("当前数量:" + entityList.size() + "\r\n");
                        for (int i = 0; i < entityList.size(); i++) {
                            sb.append(i + ": ");
                            sb.append(entityList.get(i) == null ? "" : entityList.get(i).toString());
                            sb.append("\n");
                        }
                        alert(sb.toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        findViewById(R.id.btnCallback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBound) {
                    alert("未连接到远程服务");
                    return;
                }
                try {
                    if (iRemoteService != null) {
                        final String para = "canshu";
                        iRemoteService.asyncCallSomeone(para, new IMyCallback.Stub() {
                            @Override
                            public void onSuccess(String aString) throws RemoteException {
                                alert(String.format("发送: %s, 回调: %s", para, aString));
                            }
                        });
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void alert(String str) {
        Toast.makeText(this, str, 0).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mBound) {
            attemptToBindService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }


    /**
     * 尝试与服务端建立连接
     */
    private void attemptToBindService() {
        Intent intent = new Intent();
        intent.setAction("com.example.REMOTE.myserver");
        intent.setPackage("com.example.myserver");
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(getLocalClassName(), "service connected");
            iRemoteService = IRemoteService.Stub.asInterface(service);
            mBound = true;

            if (iRemoteService != null) {
                try {
                    iRemoteService.doSomeThing(0, "anything string");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(getLocalClassName(), "service disconnected");
            mBound = false;
        }
    };
}
