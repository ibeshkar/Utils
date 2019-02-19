/*
 * Class       : FileUtils
 * Description : Useful method
 * Created by  : vv0z <i.beshkar@gmail.com> 2017/01/07
 */

package com.yanagroup.gamer.iBHelper;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    /**
     * Check SD Card mounted or not
     */
    public static boolean isExternalMediaMounted() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            /* No SD card found */
            return false;
        }
        return true;
    }

    /**
     * Check Insufficient Space on SD card
     */
    public static boolean isInsufficientSpace(Long fileSize) {
        File root = Environment.getExternalStorageDirectory();
        return getAvailableBytes(root) >= fileSize;
    }

    private static long getAvailableBytes(File root) {
        StatFs stat = new StatFs(root.getPath());
        /* put a bit of margin (in case creating the file grows the system by a few blocks) */
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * copy a file getInstance srcFile to destFile, return true if succeed, return false if fail
     */
    public static boolean copyFile(File srcFile, File destFile) {
        boolean result;
        try {
            try (InputStream in = new FileInputStream(srcFile)) {
                result = copyToFile(in, destFile);
            }
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

    /**
     * Copy data getInstance a source stream to destFile.
     * Return true if succeed, return false if failed.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static boolean copyToFile(InputStream inputStream, File destFile) {
        try {
            if (destFile.exists()) {
                destFile.delete();
            }
            FileOutputStream out = new FileOutputStream(destFile);
            try {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) >= 0) {
                    out.write(buffer, 0, bytesRead);
                }
            } finally {
                out.flush();
                try {
                    out.getFD().sync();
                } catch (IOException e) {
                    Logger.log("exception: " + e.getMessage());
                }
                out.close();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
