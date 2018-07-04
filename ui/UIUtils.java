package danny.com.sale.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import danny.com.sale.SaleApplication;

/**
 * ui工具类,可获取上下文环境，全局Handler，主线程id，通过资源id获取资源，加载布局，尺寸转换，判断处理逻辑运行在主线程还是子线程
 * Created by danny on 2018/6/10.
 */

public class UIUtils {

    //获取上下文
    public static Context getContext(){return SaleApplication.getContext();}

    //获取handler
    public static Handler getHandler(){return SaleApplication.getHandler();}

    //获取主线程id
    public static int getUiThreadId(){return SaleApplication.getUiThreadId();}

    //获取字符串
    public static String getString(int resId){return getContext().getResources().getString(resId);}

    //获取字符串数组
    public static String[] getStringArray(int resId){return getContext().getResources().getStringArray(resId);}

    //获取图片
    public static Drawable getDrawable(int resId){return getContext().getResources().getDrawable(resId);}

    //获取颜色
    public static int getColor(int resId){return getContext().getResources().getColor(resId);}

    //根据id获取颜色的状态选择器
    public static ColorStateList getColorStateList(int resId) {
        return getContext().getResources().getColorStateList(resId);
    }

    //获取像素值
    public static int getDimen(int resId){return getContext().getResources().getDimensionPixelSize(resId);}

    //获取系统认为最小滑动距离TouchSlop,当小于该值时,忽略滑动(8dp)
    public static int getTouchSlop(Context context){
        return ViewConfiguration.get(context).getScaledTouchSlop();
    }

    //dp-px
    public static int dp2px(float dp){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5f);
    }

    //px-dp
    public static float px2dp(int px){
        float density = getContext().getResources().getDisplayMetrics().density;
        return px/density;
    }

    //加载布局文件
    public static View inflate(int resId){return View.inflate(getContext(),resId,null);}

    //判断是否运行在主线程
    public static boolean isRunningInUiThread(){
        //获取当前线程id
        int myTid = android.os.Process.myTid();
        if (myTid == getUiThreadId()){
            return true;
        }
        return false;
    }

    //运行在主线程逻辑处理
    public static void runningInUiThread(Runnable runnable){
        if (isRunningInUiThread()){
            runnable.run();//在主线程直接运行
        }else {//直接用Handler发消息让其运行在主线程
            getHandler().post(runnable);
        }
    }
}
