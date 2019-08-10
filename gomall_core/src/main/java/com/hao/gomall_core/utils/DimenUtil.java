package com.hao.gomall_core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.hao.gomall_core.app.Mall;

public class DimenUtil {

    public static int getScreentWidth(){
        final Resources resources = Mall.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreentHeight(){
        final Resources resources = Mall.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
