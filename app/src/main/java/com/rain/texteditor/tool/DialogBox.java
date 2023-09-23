package com.rain.texteditor.tool;

import android.content.Context;
import android.content.DialogInterface;
import android.rain.data.io.SDFile;
import android.rain.tool.Dialog;
import android.util.Log;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AlertDialog;
import com.rain.texteditor.R;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

public class DialogBox {
    private ArrayList<String> temp_file_list;
    private String temp_file_path;
    private Context context;

    public DialogBox(Context context) {
        this.context=context;
        temp_file_path = SDFile.SD_PATH;
        temp_file_list = new ArrayList<>();
    }

    public void show(EditText editText,SDFile sdFile){
        new Dialog(context).adapterDialogBox("选择路径", getSimpleAdapter(), "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s = temp_file_list.get(which);
                File file = new File(temp_file_path, s);
                if (file.isFile()) {
                    editText.setText(sdFile.read(file));
                }else if (file.isDirectory()) {
                    temp_file_path=temp_file_path+"/"+s;
                    show(editText,sdFile);
                }
            }

        }, null).setNegativeButton("默认路径", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                temp_file_path=SDFile.SD_PATH+"/textEditor/Pixiv";
                show(editText,sdFile);
            }
        }).show();
    }
    private SimpleAdapter getSimpleAdapter() {
        ArrayList<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        String[] arr = new File(temp_file_path).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.startsWith(".");
            }
        });
        temp_file_list.clear();
        if (arr != null) {
            Arrays.sort(arr);
            for (String s : arr) {
                temp_file_list.add(s);
                map = new HashMap<String, Object>();
                if (new File(temp_file_path,s).isFile()) {
                    map.put("img", R.drawable.is_file);
                }else map.put("img", R.drawable.is_folder);
                map.put("text", s);
                listItem.add(map);
            }
        }
        return new SimpleAdapter(
                context, listItem, R.layout.list_items,
                new String[]{"img", "text"},
                new int[]{R.id.list_img, R.id.list_text}
        );
    }

}
