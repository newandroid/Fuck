package com.example.nio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/12/2.
 */

public class ScanSum {
    private static final String INPUT_PATH = "D:\\devspace\\practice\\Fuck\\thinkjava\\src\\main\\java\\com\\example\\nio\\ScanSumbers.txt";

    public void sum() {
        Scanner s = null;
        double sum = 0;
        try {
            s = new Scanner(new BufferedReader(new FileReader(INPUT_PATH)));
            s.useLocale(Locale.US);
            while (s.hasNext()) {
                if (s.hasNextDouble()) {
                    sum += s.nextDouble();
                } else {
                    s.next();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null)
                s.close();
        }
        System.out.println(sum);
    }
}
