package android.rain.tool;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AlertDialog;

/**
 * 应用程序兼容性对话框
 * Message prompt box-消息提示框
 */
public class Dialog {
    private Context context;

    public Dialog(Context context) {
        this.context = context;
    }

    /**
     * Message prompt box-消息提示框 setNegativeButton("否", null)
     *
     * @param title           对话框标签
     * @param text            对话文本
     * @param onClickListener 列表内按钮
     */
    public AlertDialog.Builder MessagePromptBox(String title, String text, String button, DialogInterface.OnClickListener onClickListener) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton(button, onClickListener);
    }

    /**
     * Text input prompt box-文本输入提示框
     *
     * @param title           对话框标签
     * @param edt             输入的文本框
     *                        edt.setMinLines(3)：设置输入框最小文本行数
     * @param onClickListener 列表内单个菜单单点击事件
     */
    public AlertDialog.Builder textInputPromptBox(String title, EditText edt, String button, DialogInterface.OnClickListener onClickListener) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setIcon(null)
                .setView(edt)
                .setPositiveButton(button, onClickListener);
    }

    /**
     * listDialogBox 列表提示框
     *
     * @param title               标签
     * @param items               列表
     * @param button              按钮名
     * @param onClickListenerList 列表点击
     * @param onClickListener     按钮点击
     */
    public AlertDialog.Builder listDialogBox(String title, CharSequence[] items, String button, DialogInterface.OnClickListener onClickListenerList, DialogInterface.OnClickListener onClickListener) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setItems(items, onClickListenerList)
                .setNegativeButton(button, onClickListener);
    }

    /**
     * listDialogBox 自定义列表提示框
     *
     * @param title               标签
     * @param button              按钮名
     * @param onClickListenerList 列表点击
     * @param onClickListener     按钮点击
     */
    public AlertDialog.Builder adapterDialogBox(String title, SimpleAdapter adapter, String button, DialogInterface.OnClickListener onClickListenerList, DialogInterface.OnClickListener onClickListener) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setAdapter(adapter, onClickListenerList)
                .setPositiveButton(button, onClickListener);
    }

    /**
     * pictureDialogBox 图片提示框
     *
     * @param title           标签
     * @param img             图片
     *                        ImageView img = new ImageView(this);
     *                        img.setImageResource(R.drawable.hua);
     * @param button          按钮名
     * @param onClickListener 按钮点击
     */
    public AlertDialog.Builder pictureDialogBox(String title, ImageView img, String button, DialogInterface.OnClickListener onClickListener) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setView(img)
                .setPositiveButton(button, onClickListener);
    }
}
