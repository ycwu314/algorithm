package com.example.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/9/24.
 * <p>
 * Please create a function to extract the filename extension from the given path,return the extracted filename extension or null if none.
 */
public class FilenameExtension {

    /**
     * using split() is slow.
     * just find the last index of '.' is fast
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try (BufferedReader bw = new BufferedReader(new InputStreamReader(System.in))) {
            String line = null;
            while ((line = bw.readLine()) != null) {

                int i = line.lastIndexOf('.');
                if (i == -1) {
                    System.out.println("null");
                    continue;
                } else {
                    System.out.println(line.substring(i+1));
                }
            }
        }
    }
}
