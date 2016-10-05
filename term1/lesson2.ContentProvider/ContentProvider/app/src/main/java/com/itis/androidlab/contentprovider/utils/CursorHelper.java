package com.itis.androidlab.contentprovider.utils;

import android.database.Cursor;

import com.itis.androidlab.contentprovider.models.Tag;
import com.itis.androidlab.contentprovider.provider.TagsContract.Tags;

import java.util.ArrayList;
import java.util.List;

public final class CursorHelper {

    private CursorHelper() {
    }

    public static List<Tag> parseTags(Cursor cursor) {
        List<Tag> parsed = new ArrayList<>();

        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            do {
                Tag tag = parseTag(cursor);
                parsed.add(tag);
            } while (!cursor.isClosed() && cursor.moveToNext());
        }

        return parsed;
    }

    private static Tag parseTag(Cursor cursor) {
        Tag tag = new Tag();
        tag.setId(cursor.getLong(cursor.getColumnIndexOrThrow(Tags.TAG_ID)));
        tag.setName(cursor.getString(cursor.getColumnIndexOrThrow(Tags.NAME)));
        return tag;
    }
}