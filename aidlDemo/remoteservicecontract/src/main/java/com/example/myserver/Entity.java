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
