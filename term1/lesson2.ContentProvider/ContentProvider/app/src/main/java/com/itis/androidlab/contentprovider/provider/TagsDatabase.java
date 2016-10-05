package com.itis.androidlab.contentprovider.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Helper for managing {@link SQLiteDatabase} that stores data for
 * {@link TagsProvider}.
 */
public class TagsDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tags.db";
    private static final int DATABASE_VERSION = 1;

    public TagsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    interface Tables {
        String TAGS = "tags";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.TAGS + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TagsContract.Tags.TAG_ID + " INTEGER NOT NULL,"
                + TagsContract.Tags.NAME + " TEXT NOT NULL,"
                + "UNIQUE (" + TagsContract.Tags.TAG_ID + ") ON CONFLICT REPLACE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop tables that have been deprecated.
        db.execSQL("DROP TABLE IF EXISTS " + Tables.TAGS);
        onCreate(db);
    }
}