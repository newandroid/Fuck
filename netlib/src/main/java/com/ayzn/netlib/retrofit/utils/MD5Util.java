package com.ayzn.netlib.retrofit.utils;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yangboy22 on 2018/1/19.
 */

public class MD5Util {

    //字符串
    public static String md5(String input) {
        if (TextUtils.isEmpty(input)) {
            return "";
        }
        byte[] out = (byte[]) null;
        try {
            byte[] btInput = input.getBytes("utf-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(btInput);
            out = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return toHexString(out);
    }

    private static String toHexString(byte[] out) {
        StringBuilder buf = new StringBuilder();
        for (byte b : out)
            buf.append(String.format("%02X", new Object[] { Byte.valueOf(b) }));

        return buf.toString();
    }


    // 计算文件的 MD5 值
    public static String md5(File file) {
        if (file == null || !file.isFile() || !file.exists()) {
            return "";
        }
        FileInputStream in = null;
        String result = "";
        byte buffer[] = new byte[8192];
        int len;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                md5.update(buffer, 0, len);
            }
            byte[] bytes = md5.digest();

            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
