package danny.com.sale;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by danny on 2018/6/10.
 */

public class CustomApplication extends Application {
    private static Context mContext;
    private static Handler mHandler;
    private static int mUiThreadId;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        mHandler = new Handler();
        mUiThreadId = android.os.Process.myTid();//主线程id（当前线程id）
    }

    public static Context getContext() {return mContext;}
    public static Handler getHandler() {return mHandler;}
    public static int getUiThreadId() {return mUiThreadId;}
}
