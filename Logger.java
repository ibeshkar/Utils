/*
 * Class       : Logger
 * Description : Show logs in debug mode
 * Created by  : vv0z <i.beshkar@gmail.com> 2017/04/10
 */

package com.yanagroup.gamer.iBHelper;

import android.util.Log;

import com.yanagroup.gamer.BuildConfig;

public class Logger {

    private static final String TAG = "Logger";
    private String log;
    private String tag;

    private Logger(String tag, String log) {
        this.tag = tag;
        this.log = log;
        d(tag, log);
    }

    public static Logger log(String tag, String log) {
        return new Logger(tag, log);
    }

    public static Logger log(String log) {
        return new Logger(TAG, log);
    }

    private void d(String tag, String log) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, log);
        }
    }

}
