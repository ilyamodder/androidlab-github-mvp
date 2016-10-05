package com.itis.androidlab.contentprovider.utils;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.itis.androidlab.contentprovider.models.Tag;
import com.itis.androidlab.contentprovider.provider.TagsContract.Tags;

/**
 * @author Valiev Timur.
 */

public final class ContentValuesHelper {

    public static ContentValues mapTagToContentValues(@NonNull Tag tag) {
        ContentValues cv = new ContentValues();
        cv.put(Tags.TAG_ID, tag.getId());
        cv.put(Tags.NAME, tag.getName());
        return cv;
    }

}
