package android.rain.data;

import android.content.Context;

public class SharedPreferences {
    private android.content.SharedPreferences sharedPreferences;
    private android.content.SharedPreferences.Editor editor;
    private Context context;
    private String xml;

    public SharedPreferences(Context context, String xml) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(xml, 0);//书籍xml记录数据
        editor = sharedPreferences.edit();
    }

    public android.content.SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(android.content.SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public android.content.SharedPreferences.Editor getEditor() {
        return editor;
    }

    public void setEditor(android.content.SharedPreferences.Editor editor) {
        this.editor = editor;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }
}
