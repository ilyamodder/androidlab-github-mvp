package com.itis.androidlab.contentprovider.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class TagsContract {


    public static final String CONTENT_TYPE_APP_BASE = "tags.";

    public static final String CONTENT_TYPE_BASE =
            "vnd.android.cursor.dir/vnd." + CONTENT_TYPE_APP_BASE;

    public static final String CONTENT_ITEM_TYPE_BASE =
            "vnd.android.cursor.item/vnd." + CONTENT_TYPE_APP_BASE;

    public interface TagsColumns {
        String TAG_ID = "tag_id";
        String NAME = "name";
    }

    public static final String CONTENT_AUTHORITY = "ru.itis.androidlab.contentprovider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_TAGS = "tags";

    public static String makeContentType(String id) {
        if (id != null) {
            return CONTENT_TYPE_BASE + id;
        } else {
            return null;
        }
    }

    public static String makeContentItemType(String id) {
        if (id != null) {
            return CONTENT_ITEM_TYPE_BASE + id;
        } else {
            return null;
        }
    }

    public static class Tags implements TagsColumns, BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TAGS).build();

        public static final String CONTENT_TYPE_ID = "tags";

        /**
         * Build a {@link Uri} that references a given tag.
         */
        public static Uri buildTagUri(String tagId) {
            return CONTENT_URI.buildUpon().appendPath(tagId).build();
        }
    }
}
