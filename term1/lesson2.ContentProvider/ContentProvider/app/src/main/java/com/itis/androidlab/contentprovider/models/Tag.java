package com.itis.androidlab.contentprovider.models;

import com.itis.androidlab.contentprovider.utils.AndroidUtils;

import java.util.Random;

/**
 * @author Valiev Timur.
 */

public class Tag {

    private long id;
    private String name;

    public Tag() {
        id = new Random().nextInt(10000);
        name = AndroidUtils.generateRandomWord();
    }

    public Tag(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
