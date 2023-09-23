package android.rain.data.io;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;

/**
 * 动态请求权限
 */
public class MyPermission {
    private Activity activity;
    private static final int REQUEST_CODE = 0;
    public MyPermission(Activity activity) {
        this.activity = activity;
    }

    /**
     * @return 是否拥有读取数据权限
     */
    public boolean isREAD_EXTERNAL_STORAGE(){
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }

    /**
     * @return 是否拥有写入数据权限
     */
    public boolean isWRITE_EXTERNAL_STORAGE(){
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }
    /**
     * 请求储存权限
     */
    public void WRPermissions(){
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        activity.requestPermissions(permissions, REQUEST_CODE);
    }
}
