package android.rain.data.io;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import java.io.*;
import java.nio.file.Files;

/**
 * 必须在build.gradle中的defaultConfig添加targetSdkVersion 24才能成功
 */
public class SDFile {
    public static String SD_PATH;
    private MyPermission myPermission;
    private Activity activity;

    public SDFile(Activity activity) {
        this.activity = activity;
        SD_PATH = Environment.getExternalStorageDirectory().getPath();
        myPermission = new MyPermission(activity);
        if (myPermission.isREAD_EXTERNAL_STORAGE() || myPermission.isWRITE_EXTERNAL_STORAGE()) {
            myPermission.WRPermissions();
        }
    }

    /**
     * @param child 路径：/storage/emulated/0/child
     * @param bytes 要写入的字节
     */
    public void write(String child, byte[] bytes) {
        String path = Environment.getExternalStorageDirectory().getPath();
        File file = new File(path, child);
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            Log.i("", "写入失败");
            e.printStackTrace();
        }
    }

    public void write(File file, byte[] bytes, boolean b) {
        try {
            FileOutputStream fos = new FileOutputStream(file, b);
            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            Log.i("", "写入失败");
            e.printStackTrace();
        }
    }

    /**
     * @param child 路径：/storage/emulated/0/child
     * @return txt文件字符串
     */
    public String read(String child) {
        String path = Environment.getExternalStorageDirectory().getPath();
        File file = new File(path, child);
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len));
            }
            fis.close();
            return sb.toString();
        } catch (IOException e) {
            Log.i("", "读取失败");
            e.printStackTrace();
        }
        return null;
    }

    public String read(File f) {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(f);
            byte[] bytes = new byte[(int) f.length()];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len));
            }
            fis.close();
            return sb.toString();
        } catch (IOException e) {
            Log.i("", "读取失败");
            e.printStackTrace();
        }
        return null;
    }

    public FileOutputStream getFileOutputStream(File f) {
        try {
            return new FileOutputStream(f, true);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public FileOutputStream getFileOutputStream(File f, boolean b) {
        try {
            return new FileOutputStream(f, b);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public FileInputStream getFileInputStream(File f) {
        try {
            return new FileInputStream(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 检测文件编码
     *
     * @param f 待检测文件
     * @return 文件编码
     */
    public String codeString(File f) {
        String code = "UTF-8";
        try {
            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(f));
            int p = (bin.read() << 8) + bin.read();
            bin.close();
            switch (p) {
                case 0xefbb:
                    code = "UTF-8";
                    break;
                case 0xfffe:
                    code = "Unicode";
                    break;
                case 0xfeff:
                    code = "UTF-16BE";
                    break;
                default:
                    code = "GBK";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    public String getLength(File file) {
        return getLength(file.length());
    }

    public String getLength(long lenB) {
        long lenK, lenG;
        if (lenB >= 1024) {
            lenK = lenB / 1024;
            if (lenK >= 1024) {
                lenG = lenK / 1024;
                return lenG + "GB";
            } else return lenK + "KB";
        } else return lenB + "B";
    }
}
