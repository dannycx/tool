package danny.com.mvvm.util;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 系统工具类
 * Created by danny on 2018/8/15.
 */

public class SystemUtil {

    /**
     * 获取系统语言
     * 
     * @return 系统语言
     */
    public static String getSystemLanguage(){
        Locale locale = Locale.getDefault();
        return locale.getLanguage();
    }

    /**
     * 获取系统时间
     * 
     * @return 系统时间
     */
    public static String getSystemTime(){
        return new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
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
}
