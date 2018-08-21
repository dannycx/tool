package danny.com.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;


/**
 * 权限请求工具类
 * Created by danny on 2018/8/21.
 */

public class RequestPermissionUtil {
    private WeakReference<Activity> activityReference;

    /**
     * 持有弱引用activity对象
     *
     * @param activity activity对象
     */
    public RequestPermissionUtil(Activity activity) {
        activityReference = new WeakReference<>(activity);
    }

    /**
     * 请求权限
     *
     * @param permissionList 请求权限的集合
     * @param requestCode 请求码
     */
    public void request(ArrayList<String> permissionList, final int requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            permissionList = checkPermission(permissionList);
            final String[] permission = permissionList.toArray(new String[permissionList.size()]);
            if (shouldShowPermission(permission)) {
                showMessage("You need allow to " + permission[0], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(activityReference.get(), permission, requestCode);
                        return;
                    }
                });
                ActivityCompat.requestPermissions(activityReference.get(), permission, requestCode);
            }
        }
    }

    /**
     * 弹出对话框，针对用户点击拒绝加不再提醒选择，增强用户体验效果
     *
     * @param s 显示消息
     * @param onClickListener 点击确定按钮，回调接口
     */
    private void showMessage(String s, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(activityReference.get()).setMessage(s).setPositiveButton("OK", onClickListener).create().show();
    }

    /**
     * 对所有权限做选择不再提醒处理
     *
     * @param permission 权限数组
     * @return
     */
    private boolean shouldShowPermission(String[] permission) {
        boolean flag = false;
        for (int i = 0; i < permission.length; i++) {
            flag = flag || ActivityCompat.shouldShowRequestPermissionRationale(activityReference.get(), permission[i]);
        }
        return flag;
    }

    /**
     * 校验是否授予该权限
     *
     * @param permissionList 权限集合
     * @return  需授权的权限集合
     */
    private ArrayList<String> checkPermission(ArrayList<String> permissionList) {
        ArrayList<String> permission = new ArrayList<>();
        for (String p : permissionList) {
            if (ActivityCompat.checkSelfPermission(activityReference.get(), p) != PackageManager.PERMISSION_GRANTED) {
                permission.add(p);
            }
        }
        return permission;
    }
}
