package com.cst.growthprocess.Utils;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 异常捕捉类，捕捉异常写入文件
 */
public class CrashHandler implements UncaughtExceptionHandler {

    private static CrashHandler sInstance;

    public static CrashHandler getInstance() {
        if (sInstance == null) {
            sInstance = new CrashHandler();
        }
        return sInstance;
    }

    private CrashHandler() {
    }

    private UncaughtExceptionHandler mPreviousHandler;

    public void init() {
        mPreviousHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            writeStringToFile(
                    Environment.getExternalStorageDirectory().getPath()
                            + "/0_chenbin/uncaughtException",
                    new SimpleDateFormat("MM-dd HH", Locale.CHINA)
                            .format(new Date()) + ".txt",
                    new StringBuilder("*TIME*:\n")
                            .append(new SimpleDateFormat("HH:mm:ss",
                                    Locale.CHINA).format(new Date()))
                            .append("\n*ERROR*:\n")
                            .append(getStackTraceString(ex))
                            .append("\n\n\n\n").toString(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mPreviousHandler.uncaughtException(thread, ex);
    }

    /**
     * @param filePath
     * @param fileName
     * @param context
     * @param isOn
     */
    public static void writeStringToFile(String filePath, String fileName,
            String context, boolean isOn) {
//        if (!Configs.IS_OUTPUT_LOT_ON) {
//            return;
//        }
        // if (!isOn) {
        // return;
        // }
        final File path = new File(filePath);
        if (!path.exists()) {
            if (!path.mkdirs()) {
                return;
            }
        }

        final File saveFile = new File(filePath + File.separator + fileName);
        if (!saveFile.exists()) {
            try {
                if (!saveFile.createNewFile()) {
                    return;
                }
            } catch (IOException e) {
                return;
            }
        }

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(saveFile, "rw");
            raf.seek(saveFile.length());
            raf.write(context.getBytes());

        } catch (Exception e) {
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                }
            }
        }
    }


    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        Throwable t = tr;
        while (t != null) {
            if (t instanceof UnknownHostException) {
                return "";
            }
            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }
}
