package com.danny.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 75955 on 2018/9/6.
 */

public class UiUtil {

    /**
     * 获取系统当前语言
     *
     * @return 系统当前语言
     */
    public static String language() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取系统当前国家
     *
     * @return 系统当前国家
     */
    public static String country() {
        return Locale.getDefault().getCountry();
    }

    /**
     * 获取设备cpu数量
     *
     * @return cpu数量
     */
    public static int cpuCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * 获取设备cpu类型
     *
     * @return
     */
    public static String cpuName() {
        if (Build.CPU_ABI.equals("arm")) {
            return "arm";
        }else if (Build.CPU_ABI.equals("")) {

        }
        return null;
    }

    /**
     * 获取系统时间
     *
     * @return 系统时间
     */
    public static long time() {
        return System.currentTimeMillis();
    }

    /**
     * 获取系统时间
     *
     * @return 系统时间    2018-09-05 21:50:30
     */
    public static String formatTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取资源文件id
     *
     * @param context 上下文
     * @param name 文件名
     * @param defType 文件类型(layout,string,drawable,mipmap等)
     * @return 资源文件id
     */
    public static int getResId(Context context, String name, String defType){
        return context.getResources().getIdentifier(name, defType, context.getPackageName());
    }

    /**
     * px转dp
     *
     * @param context 上下文对象
     * @param px 像素值
     * @return dp值
     */
    public static float px2dp(Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return px / density + 0.5f;
    }

    /**
     * dp转px
     *
     * @param context 上下文对象
     * @param dp dp值
     * @return 像素值
     */
    public static int dp2px(Context context, float dp) {
        // 方式一
//        float density = context.getResources().getDisplayMetrics().density;
//        return (int) (dp * density + 0.5f);
        // 方式二
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context 上下文对象
     * @param sp sp值
     * @return 像素值
     */
    public static int sp2px(Context context, int sp) {
        // 方式一
//        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
//        return (int) (sp * scaledDensity + 0.5f);
        // 方式二
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    /**
     * px转sp
     *
     * @param context 上下文对象
     * @param px 像素值
     * @return sp值
     */
    public static int px2sp(Context context, int px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scaledDensity + 0.5f);
    }

    /**
     * 获取屏幕宽高
     *
     * @param context 上下文对象
     * @return 屏幕宽高数组
     */
    public static int[] getScreenDisplay(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        int[] screen = {width, height};
        return screen;
    }

    /**
     * 获取字符串资源
     *
     * @param context 上下文对象
     * @param resId 资源id
     * @return 字符串
     */
    public static String getString(Context context, int resId){
        return context.getResources().getString(resId);
    }

    /**
     * 获取字符串资源数组
     *
     * @param context 上下文对象
     * @param resId 字符串数组资源id
     * @return 字符串数组
     */
    public static String[] getStringArray(Context context, int resId){
        return context.getResources().getStringArray(resId);
    }

    /**
     * 获取图片
     *
     * @param context 上下文对象
     * @param resId 资源id
     * @return 图片
     */
    public static Drawable getDrawable(Context context, int resId){return context.getResources().getDrawable(resId);}

    /**
     * 获取颜色
     *
     * @param context 上下文对象
     * @param resId 资源id
     * @return 颜色
     */
    public static int getColor(Context context, int resId){return context.getResources().getColor(resId);}

    /**
     * 根据id获取颜色的状态选择器
     *
     * @param context 上下文对象
     * @param resId 资源id
     * @return 状态选择器
     */
    public static ColorStateList getColorStateList(Context context, int resId) {
        return context.getResources().getColorStateList(resId);
    }

    //获取像素值
    public static int getDimen(Context context, int resId){
        return context.getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取系统认为最小滑动距离TouchSlop,当小于该值时,忽略滑动(8dp)
     *
     * @param context 上下文对象
     */
    public static int getTouchSlop(Context context){
        //mSlop = ViewConfiguration.getWindowTouchSlop();
        return ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /**
     * 加载布局文件
     *
     * @param context 上下文对象
     * @param resId 资源id
     * @return view
     */
    public static View inflate(Context context, int resId){
        return View.inflate(context, resId, null);
    }

    /**
     * 获取资源(例:获取drawable资源，name:drawable文件名称(shape_common),type(drawable))
     *
     * @param context 上下文对象
     * @param name 资源名称
     * @param type 资源类型
     * @return 资源
     */
    public static int resources(Context context, String name, String type){
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }

    /**
     * 
     * @param activity
     * @param visible
     */
    public static void windowPopVisible(Activity activity, boolean visible) {
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        if (visible) {
            params.alpha = 0.7f;
        }else {
            params.alpha = 1.0f;
        }
        activity.getWindow().setAttributes(params);
    }
}
