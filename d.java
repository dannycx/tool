package com.danny.zxing;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by 75955 on 2018/10/23.
 */

public class ScreenManager {
    private int screenWidth;
    private int screenHeight;

    // 水平宽高
    private int horizontalWidth;
    private int horizontalHeight;

    // 竖直宽高
    private int verticalWidth;
    private int verticalHeight;

    private static WindowManager manager;
    private static DisplayMetrics dm;

    public static void init(Context context) {
        manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        dm = new DisplayMetrics();


    }

    /**
     * Configuration.ORIENTATION_PORTRAIT 竖屏
     * @param context
     * @return
     */
    public int getWidth(Context context) {
        init(context);
        int orientation = context.getResources().getConfiguration().orientation;
        return orientation == Configuration.ORIENTATION_PORTRAIT ? screenWidth : screenHeight;
    }

    public int getHeight(Context context) {
        init(context);
        int orientation = context.getResources().getConfiguration().orientation;
        return orientation == Configuration.ORIENTATION_PORTRAIT ? screenHeight : screenWidth;
    }
}
