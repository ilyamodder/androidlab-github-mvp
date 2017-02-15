package ru.kfu.itis.androidlab.rxjava.model;

import android.support.annotation.NonNull;

public class Person {

    private final String mName;
    private final int mAge;

    public Person(@NonNull String name, int age) {
        mName = name;
        mAge = age;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public int getAge() {
        return mAge;
    }
}
