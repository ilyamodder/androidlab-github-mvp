package com.itis.androidlab.githubmvp.content;

import android.support.annotation.NonNull;

import com.google.gson.Gson;


public final class GsonHolder {

    private GsonHolder() {
    }

    @NonNull
    public static Gson getGson() {
        return Holder.GSON;
    }

    public static final class Holder {
        private static final Gson GSON = new Gson();
    }

}
