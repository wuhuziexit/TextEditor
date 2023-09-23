package android.rain.data.io;

import android.content.Context;
import java.io.*;

public class DataFile {
    private Context context;
    /**
     * @param context 一个context
     */
    public DataFile(Context context) {
        this.context = context;
    }

    /**
     * @param path 文件路径：/storage/emulated/0/Android/data/包名/files/path
     * @param content 文件内容
     */
    public void write(String path,String content) {
        try {
            String absolutePath = context.getExternalFilesDir(null).getAbsolutePath();
            File file = new File(absolutePath,path);
            FileOutputStream outputStream = new FileOutputStream(file,true);
            PrintStream printStream = new PrintStream(outputStream);
            printStream.print(content);
            printStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param path 文件路径：/storage/emulated/0/Android/data/包名/files/path
     * @return txt文件的字符串
     */
    public String read(String path) {
        try {
            String absolutePath = context.getExternalFilesDir(null).getAbsolutePath();
            File file = new File(absolutePath,path);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = fileInputStream.read(bytes)) > 0) {
                sb.append(new String(bytes, 0, len));
            }
            fileInputStream.close();
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
