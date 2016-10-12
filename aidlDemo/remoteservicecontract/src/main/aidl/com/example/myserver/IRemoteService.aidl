// IMyAidlInterface.aidl
package com.example.myserver;
import com.example.myserver.Entity;
import com.example.myserver.IMyCallback;

// Declare any non-default types here with import statements

interface IRemoteService {

    void doSomeThing(int anInt,String aString);

    void addEntity(in Entity entity);

    List<Entity> getEntity();

    void asyncCallSomeone( String para, IMyCallback callback);
}
