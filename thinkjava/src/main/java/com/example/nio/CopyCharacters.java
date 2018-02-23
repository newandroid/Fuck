package com.example.nio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/11/29.
 */

public class CopyCharacters {
    private static final String INPUT_PATH = "D:\\devspace\\practice\\Fuck\\thinkjava\\src\\main\\java\\com\\example\\nio\\xanadu.txt";
    private static final String OUTPUT_PATH = "D:\\devspace\\practice\\Fuck\\thinkjava\\src\\main\\java\\com\\example\\nio\\outagain.txt";

    public void copy() {
        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader(INPUT_PATH);
            outputStream = new FileWriter(OUTPUT_PATH);
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

    public void copyLines(){
        BufferedReader inputStream = null;
        PrintWriter outputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader(INPUT_PATH));
            outputStream = new PrintWriter(new FileWriter(OUTPUT_PATH));
            String c;
            while ((c = inputStream.readLine()) != null) {
                outputStream.write(c);
                System.out.println(c+"\n");
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
