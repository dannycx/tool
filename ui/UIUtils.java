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
