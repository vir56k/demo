#AIDL示例


#背景
最近在考虑项目重构的时候，考虑将项目拆分成两个APK，一个用于数据服务，一个用于UI展示。
数据服务APK向自己编写APK提供数据，同时也可以向第三方提供数据。考虑使用这样的方式代替向第三方提供jar形式的sdk包。
如果拆分成多个APK，不得不考虑 进程间通信(IPC)的问题。Android提供了一种IPC的实现，就是AIDL.

在学习AIDL时编写示例形成本文。放在Github的demo项目中。可以在下面的地址下载到源代码
github: https://github.com/vir56k/demo/tree/master/aidlDemo


#什么是AIDL
AIDL （Android Interface Definition Language， Android接口定义语言）
在不同的进程（应用）之间进行数据交换，就要约定 之间的通信接口。

从面向对象的角度来看，接口设计要考虑状态和行为。一般来说，接口定义的内容分为：
1.方法操作（描述行为）
2.参数（描述状态，数据的类型，数据的载体/实体)

AIDL是一种IDL，它有特有的语法描述。我们需要编写一个AIDL文件作为约定。它的语法非常类似java语法。
它支持基础数据类型，比如 int,String,float等。
它支持实体类，必须是实现了Parcelable接口，支持序列化。

AIDL通过服务绑定的方式来使用。你需要定义一个service,传递一个 IBinder对象。这个 IBinder对象具有我们需要的方法。
拿到这个对象后执行具体方法。

AIDL分为 服务端和客户端
服务端即服务提供着，提供可操作的方法和数据。
客户端即调用者，使用方法和数据。

什么时候适合使用AIDL：
官方文档建议只有你允许客户端从不同的应用程序为了进程间的通信而去访问你的service，以及想在你的service处理多线程。


#步骤说明

##服务端开发步骤如下：
1.定义一个AIDL文件
2.实现描述的接口，编写service
3.如果有实体类，需要提供实体类（jar包形式）

##客户端
1.拿到AIDL文件
2.绑定服务，获得接口持有对象。

#示例
##服务端开发
###1.声明AIDL文件
Android提供的特殊的文件夹来放置AIDL文件，位于 src/mian/aidl 文件夹下。
由于java类/接口是有 package(命名空间)的。我们需要定义命名空间，一般和文件位置一致。
在这里，我们在 src/mian/aidl 文件夹下，创建package，名称为：com.example.myserver。
对应文件夹路径为src/mian/aidl/com/example/myserver，我们在这个文件下建立我们的aidl文件，内容如下:

IRemoteService.aidl

        package com.example.myserver;
        import com.example.myserver.Entity;
        import com.example.myserver.IMyCallback;
        
        // Declare any non-default types here with import statements
        
        interface IRemoteService {
        
            void doSomeThing(int anInt,String aString);
        
            void addEntity(in Entity entity);
        
            List<Entity> getEntity();
        
        }

Entity.aidl，这个是实体类 ，它还需要对应一个java class文件

    // Entity.aidl
    package com.example.myserver;
    
    parcelable Entity;




###2.实现接口,编写service
在src/java文件夹写下 MyService class,集成服务Service类.在mainifest文件中注册这个服务类。
如果你的aidl描述文件编写无误的话，android studio 会自动帮你生成一些辅助类，你可以在下面的目录找到：
    
    build/generated/source/debug
    
在这个文件夹下回自动生成有 IRemoteService类，和它的子类 IRemoteService.Stub类及其他。感兴趣的同学可以读读。

IRemoteService.Stub是一个根文件，它是一个抽象类。下面代码演示了，一个 IRemoteService.Stub 的匿名类的实现。
在这个服务类的 public IBinder onBind(Intent intent) 方法中，我们return 一个 IRemoteService.Stub 的匿名类实现。

在客户绑定到这个服务的时候，将可以获得到这个实现的一个实例，调用它的方法。

代码如下

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
    

        };
    }


###3.编写实体类
我们上面提到，接口的参数可以是实体类。我们在前面定义了一个entity.aidl，它里面写了一句
        
         parcelable Entity;  

这么一句话指明它需要关联到一个具体的实体类。我们需要在src/java文件夹编写这么一个类的实现，必须实现parcelable接口。
注意我们要先建立package,这个 package要和aidl接口声明里的一致。
android studio为我们方便的提供自动生成parcelable实现的快捷键，在mac下是 command+空格。实现后的代码如下：


    package com.example.myserver;
    
    import android.os.Parcel;
    import android.os.Parcelable;
    
    /**
     * Created by zhangyunfei on 16/10/12.
     */
    public class Entity implements Parcelable {
        int age;
        String name;
    
        public Entity() {
        }
    
        public Entity(int age, String name) {
            this.age = age;
            this.name = name;
        }
    
        protected Entity(Parcel in) {
            age = in.readInt();
            name = in.readString();
        }
    
        public static final Creator<Entity> CREATOR = new Creator<Entity>() {
            @Override
            public Entity createFromParcel(Parcel in) {
                return new Entity(in);
            }
    
            @Override
            public Entity[] newArray(int size) {
                return new Entity[size];
            }
        };
    
        public int getAge() {
            return age;
        }
    
        public void setAge(int age) {
            this.age = age;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        @Override
        public int describeContents() {
            return 0;
        }
    
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(age);
            dest.writeString(name);
        }
    
        @Override
        public String toString() {
            return String.format("age=%s, name=%s", age, name);
        }
    }


##客户端开发 -  调用AIDL接口
再开始之前，我们可以新建一个app来做演示.步骤如下：
###1.获得AIDL,放到项目中
我们先拿到AIDL描述文件才用使用，将AIDL文件放到aidl文件夹下。android studio 自动生成根文件类。
获得实体类Entity.class ，放到项目中。


###2.在activity中调用
在它的 MainActivity 下绑定服务

         Intent intent = new Intent();
            intent.setAction("com.example.REMOTE.myserver");
            intent.setPackage("com.example.myserver");
            bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

指定服务的名称，bindService方法中需要传入一个 ServiceConnection对象。
我们写一个ServiceConnection的匿名类，在它的onServiceConnected方法中，获得 aidl定义的接口持有类。

                        iRemoteService = IRemoteService.Stub.asInterface(service);

还记得刚刚编写服务类返回的 binder吗，在这里获得的就是那个binder示例。我们可以通过对这个示例进行 转型 后的对象来调用 接口定义的方法。

###3.调用接口方法
通过  iRemoteService.addEntity(entity) 方法，我们可以操作具体的实体，传入实体类作为参数。

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

完整代码如下：

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


##回调
在AIDL中，有时候需要实现回调，传入一个回调callbak，或者listener类。如何实现呢？

###1.编写回调类aidl文件
IMyCallback类具有一个 onSuccess回调方法
IMyCallback.aidl，这个文件里描述一个回调接口

    // IMyCallback.aidl
    package com.example.myserver;
    
    // Declare any non-default types here with import statements
    
    interface IMyCallback {
        /**
         * Demonstrates some basic types that you can use as parameters
         * and return values in AIDL.
         */
        void onSuccess(String aString);
    }

###2.声明方法，以回调类作为参数，示例：
IRemoteService.aidl

        package com.example.myserver;
        import com.example.myserver.Entity;
        import com.example.myserver.IMyCallback;
        
        // Declare any non-default types here with import statements
        
        interface IRemoteService {
       
            void asyncCallSomeone( String para, IMyCallback callback);
        }

###3.实现方法，发起回调通知
发起回调有点类似广播的方式，示例：    

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

我们需要一个 RemoteCallbackList 集合类，把 要回调的类的示例callback示例放到这集合内。调用这个集合类RemoteCallbackList的下面两个方法：
beginBroadcast 开始广播，finishBroadcast 结束广播，配合使用。

###4.客户端调用示例：
客户端在获得接口操作对象后，传入回调类，示例：

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

#参考
谷歌官方文档


