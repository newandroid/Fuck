package css.com.fuck.net;


import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import css.com.fuck.app.ApplicationInit;
import css.com.fuck.common.Constants;
import css.com.fuck.utils.AES256Cipher;
import css.com.fuck.utils.MD5Utils;
import css.com.fuck.utils.VersionUtil;
import css.com.fuck.utils.WifiUtils;


public final class ParamsHelper {
    public static Map<String, String> buildLoginParams(String username, String password) {
        Map<String, String> paramsMap = ParamsHelper.addCommonParams();
        try {
            username = AES256Cipher.AES_Encode(username);
            password = AES256Cipher.AES_Encode(password);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        paramsMap.put("username", username);
        paramsMap.put("password", password);
        String sign = ParamsHelper.getSign(paramsMap);
        paramsMap.put("sign", sign);
        return paramsMap;
    }
    public static Map<String, String> buildUserInfoParams(String uid, String token) {
        Map<String, String> paramsMap = ParamsHelper.addCommonParams();
        paramsMap.put("uid", uid);
        paramsMap.put("token", token);
        String sign = ParamsHelper.getSign(paramsMap);
        paramsMap.put("sign", sign);
        return paramsMap;
    }
    public static Map<String, String> updateParams() {
        Map<String, String> paramsMap = ParamsHelper.addCommonParams();
        String sign = ParamsHelper.getSign(paramsMap);
        paramsMap.put("sign", sign);
        return paramsMap;
    }
    public static Map<String, String> deleteKeyParams(String uid, String token,String id) {
        Map<String, String> paramsMap = ParamsHelper.addCommonParams();
        paramsMap.put("uid", uid);
        paramsMap.put("token", token);
        paramsMap.put("id", id);
        String sign = ParamsHelper.getSign(paramsMap);
        paramsMap.put("sign", sign);
        return paramsMap;
    }
    public static Map<String, String> buildUserTypeParams(String uid, String token) {
        Map<String, String> paramsMap = ParamsHelper.addCommonParams();
        paramsMap.put("uid", uid);
        paramsMap.put("token", token);
        String sign = ParamsHelper.getSign(paramsMap);
        paramsMap.put("sign", sign);
        return paramsMap;
    }
    public static Map<String, String> buildRegisterParams(String phoneStr, String captchaStr, String password) {
        Map<String, String> paramMap = ParamsHelper.addCommonParams();
        try {
            phoneStr = AES256Cipher.AES_Encode(phoneStr);
            password = AES256Cipher.AES_Encode(password);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        paramMap.put("username", phoneStr);
        paramMap.put("password", password);
        paramMap.put("vcode", captchaStr);
        String sign = ParamsHelper.getSign(paramMap);
        paramMap.put("sign", sign);
        return paramMap;
    }
    public static Map<String, String> buildCaptchaParams(String username, String vCodeType) {
        Map<String, String> paramsMap = ParamsHelper.addCommonParams();
        try {
            username = AES256Cipher.AES_Encode(username);
        } catch (NoSuchPaddingException | InvalidAlgorithmParameterException
                | UnsupportedEncodingException | IllegalBlockSizeException
                | BadPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        paramsMap.put("username", username);
        paramsMap.put("type", vCodeType);
        String sign = ParamsHelper.getSign(paramsMap);
        paramsMap.put("sign", sign);
        return paramsMap;
    }

    public static Map<String, String> buildBuildingParams(String uid, String token) {
        Map<String, String> paramsMap = ParamsHelper.addCommonParams();
        paramsMap.put("uid", uid);
        paramsMap.put("token", token);
        String sign = ParamsHelper.getSign(paramsMap);
        paramsMap.put("sign", sign);
        return paramsMap;
    }
    public static Map<String, String> buildUserDevicesParams(String uid, String token) {
        Map<String, String> paramsMap = ParamsHelper.addCommonParams();
        paramsMap.put("uid", uid);
        paramsMap.put("token", token);
        String sign = ParamsHelper.getSign(paramsMap);
        paramsMap.put("sign", sign);
        return paramsMap;
    }
    public static Map<String, String> buildUnlockParams(String uid, String token,String deviceMac) {
        Map<String, String> paramsMap = ParamsHelper.addCommonParams();
        paramsMap.put("uid", uid);
        paramsMap.put("token", token);
        paramsMap.put("device_mac", deviceMac);
        String sign = ParamsHelper.getSign(paramsMap);
        paramsMap.put("sign", sign);
        return paramsMap;
    }
    public static Map<String, String> buildAddDviceParams(String uid, String token,String deviceMac,String name,String unitId,String route) {
        Map<String, String> paramsMap = ParamsHelper.addCommonParams();
        paramsMap.put("uid", uid);
        paramsMap.put("token", token);
        paramsMap.put("device_mac", deviceMac);
        paramsMap.put("name", name);
        paramsMap.put("unit_id", unitId);
        paramsMap.put("route", route);
        String sign = ParamsHelper.getSign(paramsMap);
        paramsMap.put("sign", sign);
        return paramsMap;
    }
    public static Map<String, String> buildResetPasswordParams(String username, String password, String captcha) {
        Map<String, String> paramsMap = ParamsHelper.addCommonParams();
        try {
            username = AES256Cipher.AES_Encode(username);
            password = AES256Cipher.AES_Encode(password);
        } catch (NoSuchPaddingException | InvalidAlgorithmParameterException
                | IllegalBlockSizeException | UnsupportedEncodingException
                | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        paramsMap.put("username", username);
        paramsMap.put("password", password);
        paramsMap.put("vcode", captcha);
        String sign = ParamsHelper.getSign(paramsMap);
        paramsMap.put("sign", sign);
        return paramsMap;
    }

    private static Comparator myComparator = new Comparator<String>() {
        @Override
        public int compare(String cmp1, String cmp2) {
            return cmp1.compareTo(cmp2);
        }
    };

    public static String getSign(Map<String, String> paramsMap) {
        StringBuilder sign = new StringBuilder();
        String[] params = paramsMap.keySet().toArray(new String[]{});
        Arrays.sort(params, myComparator);
        int number = 0;
        for (String str : params) {
            if (number > 0) {
                sign.append("&");
            }
            sign.append(str + "=" + paramsMap.get(str));
            number++;
        }
        sign.append("&key=" + Constants.SIGN_EXTRA);
        return MD5Utils.stringToMD5(sign.toString());
    }


    private static Map<String, String> addCommonParams() {
        Map<String, String> paramsMap = new HashMap<String, String>();
//        String mac = getMac();
        String mac = WifiUtils.getMacAddress(ApplicationInit.getInstance());
        if (mac.contains(":")){
            mac = mac.replace(":","");
            mac = mac.toUpperCase();
        }
        String version = getVersion();
        String rtime = System.currentTimeMillis() + "";
        paramsMap.put("productId", Constants.PRODUCT_ID);
        paramsMap.put("mac", mac);
        paramsMap.put("version", version);
        paramsMap.put("rtime", rtime);
        paramsMap.put("platform", "1"); //1：Android、2：iOS
        return paramsMap;
    }

    public static String getMac() {
        return WifiUtils.getMacAddress();
    }

    public static String getVersion() {
        return VersionUtil.getVersionCode() + "";
    }
}
