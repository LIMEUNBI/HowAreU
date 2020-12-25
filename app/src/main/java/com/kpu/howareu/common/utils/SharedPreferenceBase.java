package com.kpu.howareu.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.kpu.howareu.common.CommonLibrary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SharedPreferenceBase {
    static final String TAG = SharedPreferenceBase.class.getSimpleName();

    public static void putPrefString(Context context, String key, String value) {
        if (context == null) {
            context = CommonLibrary.getContext();
        }

        SharedPreferences prefs = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPrefString(Context context, String key, String defValue) {
        if (context == null) {
            context = CommonLibrary.getContext();
        }

        SharedPreferences prefs = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return prefs.getString(key, defValue);
    }

    public static void putPrefLong(Context context, String key, long value) {
        if (context == null) {
            context = CommonLibrary.getContext();
        }

        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static long getPrefLong(Context context, String key, long defValue) {
        if (context == null) {
            context = CommonLibrary.getContext();
        }

        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        return prefs.getLong(key, defValue);
    }

    public static void putPrefInt(Context context, String key, int value) {
        if (context == null) {
            context = CommonLibrary.getContext();
        }

        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getPrefInt(Context context, String key, int defValue) {
        if (context == null) {
            context = CommonLibrary.getContext();
        }

        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        return prefs.getInt(key, defValue);
    }

    public static void putPrefBoolean(Context context, String key, boolean value) {
        if (context == null) {
            context = CommonLibrary.getContext();
        }

        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getPrefBoolean(Context context, String key, boolean defValue) {
        if (context == null) {
            context = CommonLibrary.getContext();
        }

        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        return prefs.getBoolean(key, defValue);
    }

    public static void serialize(File file, Object object) {
        FileOutputStream fos = null;
        ObjectOutput out = null;
        try {
            fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);

            out.writeObject(object);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
