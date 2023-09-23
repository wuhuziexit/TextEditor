package com.rain.texteditor.tool;

import android.util.Log;

public class PFile {
    public String PReplace(String st) {
        StringBuilder sb = new StringBuilder();
        String author ="";
        String[] split = st.split("\\n");
        for (String s : split) {
            s=s.replace(" ","");
            if (!s.equals("")) {
                if (s.contains("作者：")&&s.contains("pixiv")) {
                    author=s;
                }
                s = "    "+s+System.lineSeparator();
                s=s.replace(author,"");
                s=s.replace("=====第1页，共1页=====","");
                sb.append(s);
            }
        }
        return "    序章 简介\n    "+author.replace(")","\n").replace("(","\n    ")+sb.toString().replace("\n    \n    ","");
    }
}
