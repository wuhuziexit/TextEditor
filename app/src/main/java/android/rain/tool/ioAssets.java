package android.rain.tool;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
//import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;

/**
 * implementation 'com.github.albfernandez:juniversalchardet:2.4.0'
 */
public class ioAssets {
    private Context context;

    public ioAssets() {
    }

    public ioAssets(Context context) {
        this.context = context;
    }

    /**
     * 获取txt目录下文件名集合
     * path："txt书籍/原始"
     * @return txt目录下文件名集合
     */
    public String[] getAssetsTxt(String path) {
        String[] assets = null;
        try {
            assets = context.getResources().getAssets().list(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return assets;
    }
    /**
     * 传入文本路径，返回文本全部内容
     * txtPath："txt书籍/"+source+"/"+text+".txt"
     * @param txtPath 文本名
     * @return 文本内容
     */
    public String TextContent(String txtPath) {
        try {
            InputStream inputStream = context.getAssets().open(txtPath);
            String encoding;
            encoding = getEncoding(txtPath);
            Log.i("编码",encoding);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,encoding));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + System.lineSeparator());
            }
            reader.close();
            inputStream.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 检测文件编码
     * @param txtPath 待检测文件
     * @return 文件编码
     */
    public String getEncoding(String txtPath) {
        String code = "UTF-8";
        try {
            BufferedInputStream bin = new BufferedInputStream(context.getAssets().open(txtPath));
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
//    private String getEncoding(String txtPath){
//        String encoding ;
//        try {
//            InputStream inputStream = context.getAssets().open(txtPath);
//            encoding = UniversalDetector.detectCharset(inputStream);
//            inputStream.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        if (encoding == null) {
//            Toast.makeText(context, "编码错误", Toast.LENGTH_SHORT).show();
//            return null;
//        }
//        return encoding;
//    }
    public BufferedReader getTextBufferedReader(String txtPath) {
        try {
            InputStream inputStream = context.getAssets().open(txtPath);
            String encoding;
            encoding = getEncoding(txtPath);
            Log.i("编码",encoding);
            return new BufferedReader(new InputStreamReader(inputStream,encoding));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getAuthor(String txtPath) {
        try {
            InputStream inputStream = context.getAssets().open(txtPath);
            String encoding;
            encoding = getEncoding(txtPath);
            if (encoding == null) {
                inputStream.close();
                Log.i("编码","未知错误");
                return "未知错误";
            }
            Log.i("编码",encoding);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,encoding));
            StringBuilder sb = new StringBuilder();
            String line;
            for (int i = 0; i < 10; i++) {
                line = reader.readLine();
                if (line.contains("作者：")) {
                    reader.close();
                    inputStream.close();
                    return line.substring(line.indexOf("：")+1);
                }
            }
            reader.close();
            inputStream.close();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
