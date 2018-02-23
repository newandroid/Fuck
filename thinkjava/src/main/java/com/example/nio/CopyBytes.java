package com.example.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/29.
 */

public class CopyBytes {
    private static final String INPUT_PATH = "D:\\devspace\\practice\\Fuck\\thinkjava\\src\\main\\java\\com\\example\\nio\\xanadu.txt";
    private static final String OUTPUT_PATH = "D:\\devspace\\practice\\Fuck\\thinkjava\\src\\main\\java\\com\\example\\nio\\outagain.txt";

    public void isInputExist() {
        File file = new File(INPUT_PATH);
        System.out.println(file.exists());
    }

    public void copy() {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(INPUT_PATH);
            outputStream = new FileOutputStream(OUTPUT_PATH);
            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
