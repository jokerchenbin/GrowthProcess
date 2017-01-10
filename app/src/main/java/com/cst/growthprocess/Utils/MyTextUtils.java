package com.cst.growthprocess.Utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import java.nio.charset.Charset;

/**
 * Created by chengyun on 2014/4/29.
 */
public class MyTextUtils {

    /**
     * 设置字符串编码格式（utf-8）
     */
    public static byte[] getBytesUtf8(String string) {
        return getBytesUnchecked(string, "UTF-8");
    }

    public static byte[] getBytesUnchecked(String string, String charsetName) {
        if (string == null) {
            return null;
        }
        try {
            return string.getBytes(charsetName);
        } catch (Exception e) {
            return null;
        }
    }

    private enum CheckType {
        EMPTY,
        NOT_EMPTY,
        BLANK,
        NOT_BLANK;
    }

    private static boolean isAllCheck(CheckType checkType, CharSequence str, CharSequence... strs) {
        if (!isCheck(checkType, str)) {
            return false;
        }

        if (strs != null && strs.length > 0) {
            for (CharSequence strItem : strs) {
                if (!isCheck(checkType, strItem)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isCheck(CheckType checkType, CharSequence str) {
        switch (checkType) {
            case EMPTY:
                return isEmpty(str);
            case NOT_EMPTY:
                return isNotEmpty(str);
            case BLANK:
                return isBlank(str);
            case NOT_BLANK:
                return isNotBlank(str);
        }

        return false;
    }


    /**
     * 字符串是否都为空
     */
    public static boolean isAllEmpty(CharSequence str, CharSequence... strs) {
        return isAllCheck(CheckType.EMPTY, str, strs);
    }

    /**
     * 字符串是否都不为空
     */
    public static boolean isAllNotEmpty(CharSequence str, CharSequence... strs) {
        return isAllCheck(CheckType.NOT_EMPTY, str, strs);
    }

    /**
     * 字符串是否都为空白
     */
    public static boolean isAllBlank(CharSequence str, CharSequence... strs) {
        return isAllCheck(CheckType.BLANK, str, strs);
    }

    /**
     * 字符串是否都不为空白
     */
    public static boolean isAllNotBlank(CharSequence str, CharSequence... strs) {
        return isAllCheck(CheckType.NOT_BLANK, str, strs);
    }


    /**
     * 字符串是否为空
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() < 1;
    }

    /**
     * 字符串是否不为空
     */
    public static boolean isNotEmpty(CharSequence str) {
        return str != null && str.length() > 0;
    }


    /**
     * <p>
     * Checks if a String is whitespace, empty ("") or null.
     * </p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     */
    public static boolean isBlank(CharSequence str) {
        if (str == null) {
            return true;
        }

        final int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }


    /**
     * 获取字符串字节长度
     */
    public static int getByteLen(String content) {
        if (isEmpty(content)) {
            return 0;
        }
        return content.getBytes(Charset.forName("UTF-8")).length;
    }

    public static void setClipboard(Context context, String content) {
        if (content == null) {
            return;
        }
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
//        cmb.setText(content);
        ClipData data = ClipData.newPlainText("", content);
        cmb.setPrimaryClip(data);
    }

    public static boolean checkStringInStrings(String string, String[] strings) {
        if (string == null || strings == null || strings.length < 1) {
            return false;
        }

        for (String str : strings) {
            if (string.equals(str)) {
                return true;
            }
        }

        return false;
    }

    //==========================================================
    // 将字符串解析成数字
    //==========================================================

    /**
     * 将字符串解析成 byte 类型的数字。
     *
     * @return 如果无法解析，返回 0 。
     */
    public static byte parseByte(CharSequence string) {
        return parseByte(string, 10);
    }

    /**
     * 将字符串解析成 byte 类型的数字。
     *
     * @return 如果无法解析，返回 0 。
     */
    public static byte parseByte(CharSequence string, int radix) {
        return (byte) parseNumber(NumberType.BYTE, string, radix);
    }


    /**
     * 将字符串解析成 short 类型的数字。
     *
     * @return 如果无法解析，返回 0 。
     */
    public static short parseShort(CharSequence string) {
        return parseShort(string, 10);
    }

    /**
     * 将字符串解析成 short 类型的数字。
     *
     * @return 如果无法解析，返回 0 。
     */
    public static short parseShort(CharSequence string, int radix) {
        return (short) parseNumber(NumberType.SHORT, string, radix);
    }


    /**
     * 将字符串解析成 int 类型的数字。
     *
     * @return 如果无法解析，返回 0 。
     */
    public static int parseInt(CharSequence string) {
        return parseInt(string, 10);
    }

    /**
     * 将字符串解析成 int 类型的数字。
     *
     * @return 如果无法解析，返回 0 。
     */
    public static int parseInt(CharSequence string, int radix) {
        return (int) parseNumber(NumberType.INT, string, radix);
    }


    /**
     * 将字符串解析成 long 类型的数字。
     *
     * @return 如果无法解析，返回 0 。
     */
    public static long parseLong(CharSequence string) {
        return parseLong(string, 10);
    }

    /**
     * 将字符串解析成 long 类型的数字。
     *
     * @return 如果无法解析，返回 0 。
     */
    public static long parseLong(CharSequence string, int radix) {
        return (long) parseNumber(NumberType.LONG, string, radix);
    }


    /**
     * 将字符串解析成 float 类型的数字。
     *
     * @return 如果无法解析，返回 0 。
     */
    public static float parseFloat(CharSequence string) {
        return parseFloat(string, 10);
    }

    /**
     * 将字符串解析成 float 类型的数字。
     *
     * @return 如果无法解析，返回 0 。
     */
    public static float parseFloat(CharSequence string, int radix) {
        return (float) parseNumber(NumberType.FLOAT, string, radix);
    }


    /**
     * 将字符串解析成 double 类型的数字。
     *
     * @return 如果无法解析，返回 0 。
     */
    public static double parseDouble(CharSequence string) {
        return parseDouble(string, 10);
    }

    /**
     * 将字符串解析成 double 类型的数字。
     *
     * @return 如果无法解析，返回 0 。
     */
    public static double parseDouble(CharSequence string, int radix) {
        return (double) parseNumber(NumberType.DOUBLE, string, radix);
    }


    public enum NumberType {
        BYTE, SHORT, INT, LONG, FLOAT, DOUBLE;
    }

    private static Number parseNumber(NumberType numberType, CharSequence string, int radix) {
        try {
            switch (numberType) {
                case BYTE:
                    return Byte.parseByte(string.toString(), radix);
                case SHORT:
                    return Short.parseShort(string.toString(), radix);
                case INT:
                    return Integer.parseInt(string.toString(), radix);
                case LONG:
                    return Long.parseLong(string.toString(), radix);
                case FLOAT:
                    return Float.parseFloat(string.toString());
                case DOUBLE:
                    return Double.parseDouble(string.toString());
            }
        } catch (Throwable tr) {
            tr.printStackTrace();
        }

        return 0;
    }


    /**
     * 检查字符串能否解析成对应类型的数字。
     *
     * @return 如果能解析返回 true，否则返回 false 。
     */
    public static boolean canParseNumber(NumberType numberType, CharSequence string) {
        return canParseNumber(numberType, string, 10);
    }

    /**
     * 检查字符串能否解析成对应类型的数字。
     *
     * @return 如果能解析返回 true，否则返回 false 。
     */
    public static boolean canParseNumber(NumberType numberType, CharSequence string, int radix) {
        try {
            switch (numberType) {
                case BYTE:
                    Byte.parseByte(string.toString(), radix);
                case SHORT:
                    Short.parseShort(string.toString(), radix);
                case INT:
                    Integer.parseInt(string.toString(), radix);
                case LONG:
                    Long.parseLong(string.toString(), radix);
                case FLOAT:
                    Float.parseFloat(string.toString());
                case DOUBLE:
                    Double.parseDouble(string.toString());
            }
        } catch (Throwable tr) {
            return false;
        }

        return true;
    }

    /**
     * 隐藏手机号中间几位
     */
    public static String getHideMobileNo(String mobileNo) {
        if (mobileNo == null) {
            return mobileNo;
        }
        int len = mobileNo.length();
        if (len < 11) {
            return mobileNo;
        } else {
            return mobileNo.substring(0, 3) + "*****" + mobileNo.substring(8, len);
        }
    }

    /**
     * Returns a string containing the tokens joined by delimiters.
     *
     * @param tokens an array objects to be joined. Strings will be formed from
     *               the objects by calling object.toString().
     */
    public static String join(CharSequence delimiter, Iterable tokens, CharSequence tokenPrefix,
            CharSequence tokenSuffix) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (Object token : tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }

            if (tokenPrefix != null) {
                sb.append(tokenPrefix);
            }
            
            sb.append(token);

            if (tokenSuffix != null) {
                sb.append(tokenSuffix);
            }
        }
        return sb.toString();
    }

}
