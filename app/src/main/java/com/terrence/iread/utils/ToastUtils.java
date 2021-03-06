package com.terrence.iread.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.terrence.iread.IReaderApplication;


public class ToastUtils {
    private static Context context = IReaderApplication.getAppContext();
    private static Toast toast;

    public static void show(@StringRes int resId) {
        show(context.getResources().getString(resId));
    }

    public static void show(CharSequence text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
