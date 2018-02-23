package com.example.nio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/11/30.
 */

public class ScanText {
    private static final String INPUT_PATH = "D:\\devspace\\practice\\Fuck\\thinkjava\\src\\main\\java\\com\\example\\nio\\xanadu.txt";

    public void show() {
        Scanner s = null;
        try {
            s = new Scanner(new BufferedReader(new FileReader(INPUT_PATH)));
            s.useDelimiter(",\\s*");
            while (s.hasNext()) {
                System.out.println(s.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) s.close();
        }

    }
}
