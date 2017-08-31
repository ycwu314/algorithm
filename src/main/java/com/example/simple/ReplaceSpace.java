package com.example.simple;

/**
 * Created by Administrator on 2017/8/31.
 */
public class ReplaceSpace {

    public String replaceSpace(StringBuffer str) {
        StringBuilder sb = new StringBuilder(str.length() + 30);
        final String SPACE = "%20";
        for (int i = 0, strLen = str.length(); i < strLen; i++) {
            char ch = str.charAt(i);
            if (ch == ' ') {
                sb.append(SPACE);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
