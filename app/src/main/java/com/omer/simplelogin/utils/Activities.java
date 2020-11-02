package com.omer.simplelogin.utils;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;

import com.omer.simplelogin.R;

public class Activities {

    public static <T> void navigateToActivity(Context context, Class<T> clazz) {
        Intent intent = new Intent(context, clazz);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.fadein, R.anim.fadeout);
        context.startActivity(intent, options.toBundle());
    }
}
