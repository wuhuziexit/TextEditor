package com.rain.texteditor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.rain.data.io.SDFile;
import android.rain.statusbar.StatusBar;
import android.rain.tool.Dialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.rain.texteditor.tool.DialogBox;
import com.rain.texteditor.tool.PFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

public class MainActivity extends AppCompatActivity {
    private Button but_select_file, button_p_1, button_p_2, button_p_3, button_p_4, button_p_5, button_p_6, button_p_7, button_p_8;
    private androidx.appcompat.widget.Toolbar toolbar;
    private EditText editText;
    private SDFile sdFile;
    private File P_FILE_2, fPath1, fPath2;
    private PFile pFile;
    private Dialog dialog;
    private String[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewsId();
        init();
        new StatusBar(this).hide();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setViewsOnClickListener();

    }

    private void setViewsId() {
        sdFile = new SDFile(this);
        pFile = new PFile();
        dialog = new Dialog(MainActivity.this);
        but_select_file = findViewById(R.id.select_file);
        editText = findViewById(R.id.editText);
        button_p_1 = findViewById(R.id.b_1);
        button_p_2 = findViewById(R.id.b_2);
        button_p_3 = findViewById(R.id.b_3);
        button_p_4 = findViewById(R.id.b_4);
        button_p_5 = findViewById(R.id.b_5);
        button_p_6 = findViewById(R.id.b_6);
    }

    private void init() {
        fPath1 = new File(SDFile.SD_PATH, "textEditor/Pixiv/1");
        fPath2 = new File(SDFile.SD_PATH, "textEditor/Pixiv/2");
        if (!fPath2.isDirectory() || !fPath1.isDirectory()) {
            fPath2.mkdirs();
            fPath1.mkdirs();
            Log.i("", "创建");
        }
        P_FILE_2 = new File(SDFile.SD_PATH, "textEditor/Pixiv/2/1.txt");
    }

    private void setViewsOnClickListener() {
        but_select_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "需要选择文件"), 1);
            }
        });
        button_p_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setEnabled(!editText.isEnabled());
            }
        });
        button_p_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream fos = sdFile.getFileOutputStream(P_FILE_2, false);
                list = fPath1.list();
                int n = 0;
                if (list != null && list.length != 0) {
                    for (String s : list) {
                        try {
                            FileInputStream fis = sdFile.getFileInputStream(new File(fPath1, s));
                            byte[] bytes = new byte[1024];
                            int len;
                            n++;
                            fos.write(("第"+n+"章\n").getBytes());
                            while (((len = fis.read(bytes)) != -1)) {
                                fos.write(bytes, 0, len);
                            }
                            fis.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else Toast.makeText(MainActivity.this, "相应目录下无文件", Toast.LENGTH_SHORT).show();
                editText.setText(sdFile.read(new File(fPath2, "1.txt")));
            }
        });
        button_p_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(P_FILE_2.isFile() + "," + (editText.getText().toString().length() != 0), "");
                if (P_FILE_2.isFile() && editText.getText().toString().length() != 0) {
                    sdFile.write(P_FILE_2, editText.getText().toString().getBytes(), false);
                    Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(MainActivity.this, "保存失败，文件不存在或无文字", Toast.LENGTH_SHORT).show();
            }
        });
        button_p_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().length() != 0) {
                    editText.setText(pFile.PReplace(editText.getText().toString()));
                    Toast.makeText(MainActivity.this, "重置成功", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(MainActivity.this, "没有文字", Toast.LENGTH_SHORT).show();
            }
        });
        button_p_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = fPath1.list();
                String data = "文件夹1：\n" + "\r";
                if (list != null) {
                    for (String s : list) {
                        File file = new File(fPath1, s);
                        data = data + s + "(" + sdFile.codeString(file) + ")" + sdFile.getLength(file) + "\n";
                    }
                }
                data = data + "文件夹2：" + System.lineSeparator() + P_FILE_2.getName() + "(" + sdFile.codeString(P_FILE_2) + ")" + sdFile.getLength(P_FILE_2) + "\n";
                long l = fPath1.length() - fPath2.length();
                String temp = "增加了";
                if (l<0){
                    l=-l;
                    temp="减少了";
                }else if (l == 0) {
                    temp="变化了";
                }
                data = data + "对比：文件2比文件1" +temp+ sdFile.getLength(l);
                dialog.MessagePromptBox("信息", data, "确定", null).show();
            }
        });
        button_p_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBox dialogBox = new DialogBox(MainActivity.this);
                dialogBox.show(editText, sdFile);
            }
        });
    }


    /*第一个参数：这个整数requestCode提供给onActivityResult，是以便确认返回的数据是从哪个Activity返回的。
    这个requestCode和startActivityForResult中的requestCode相对应。
    第二个参数：这整数resultCode是由子Activity通过其setResult()方法返回。
    第三个参数：一个Intent对象，带有返回的数据。
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String filePath = uri.getPath();
            Toast.makeText(this, filePath, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.function_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.id_p:
                Toast.makeText(this, "”点击了添加选项”", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}