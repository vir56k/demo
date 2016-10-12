// Callback.aidl
package com.example.myserver;

// Declare any non-default types here with import statements

interface IMyCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onSuccess(String aString);
}
