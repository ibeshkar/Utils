/*
 * Class       : PermissionUtil
 * Description : Show alert dialog to user for get permissions
 * Created by  : vv0z <i.beshkar@gmail.com> 2017/10/25
 */

package com.yanagroup.gamer.iBHelper;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import static com.yanagroup.gamer.iBHelper.PermissionUtil.RequestCode.CAMERA_REQUEST_CODE;
import static com.yanagroup.gamer.iBHelper.PermissionUtil.RequestCode.READ_CONTACTS_REQUEST_CODE;
import static com.yanagroup.gamer.iBHelper.PermissionUtil.RequestCode.READ_PHONE_STATE_REQUEST_CODE;
import static com.yanagroup.gamer.iBHelper.PermissionUtil.RequestCode.READ_SMS_REQUEST_CODE;
import static com.yanagroup.gamer.iBHelper.PermissionUtil.RequestCode.RECEIVE_SMS_REQUEST_CODE;
import static com.yanagroup.gamer.iBHelper.PermissionUtil.RequestCode.WRITE_EXTERNAL_STORAGE_REQUEST_CODE;

public class PermissionUtil {


    public interface PermissionAskListener {

        // Callback on permission denied previous
        void onPreviouslyDenied();

        // Callback on permission granted previous
        void onPreviouslyGranted();
    }

    /**
     * Request codes
     */
    public static final class RequestCode {

        public static final int MULTI_PERMISSIONS_REQUEST_CODE = 100;
        public static final int READ_PHONE_STATE_REQUEST_CODE = 200;
        public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 300;
        public static final int CAMERA_REQUEST_CODE = 400;
        public static final int READ_CONTACTS_REQUEST_CODE = 500;
        public static final int READ_SMS_REQUEST_CODE = 600;
        public static final int RECEIVE_SMS_REQUEST_CODE = 700;
    }

    /**
     * Check version of android
     */
    private static boolean shouldAskPermission() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
    }

    /**
     * Get READ_PHONE_STATE permission
     */
    public static void getReadPhoneState(Activity activity, PermissionAskListener listener) {

        if (shouldAskPermission()) {

            int result = ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.READ_PHONE_STATE);

            if (result != PackageManager.PERMISSION_GRANTED) {

                // If permission denied previously
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.READ_PHONE_STATE)) {

                    listener.onPreviouslyDenied();

                } else {

                    // Permission denied or first time requested
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.READ_PHONE_STATE},
                            READ_PHONE_STATE_REQUEST_CODE
                    );

                }

            } else {

                listener.onPreviouslyGranted();
            }

        }
    }

    /**
     * Get WRITE_EXTERNAL_STORAGE permission
     */
    public static void getWriteExternalStorage(Activity activity, PermissionAskListener listener) {

        if (shouldAskPermission()) {

            int result = ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (result != PackageManager.PERMISSION_GRANTED) {

                // If permission denied previously
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    listener.onPreviouslyDenied();

                } else {

                    // Permission denied or first time requested
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE
                    );

                }

            } else {

                listener.onPreviouslyGranted();
            }

        }

    }

    /**
     * Get CAMERA permission
     */
    public static void getCamera(Activity activity, PermissionAskListener listener) {

        if (shouldAskPermission()) {

            int result = ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.CAMERA);

            if (result != PackageManager.PERMISSION_GRANTED) {

                // If permission denied previously
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.CAMERA)) {

                    listener.onPreviouslyDenied();

                } else {

                    // Permission denied or first time requested
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.CAMERA},
                            CAMERA_REQUEST_CODE
                    );

                }

            } else {

                listener.onPreviouslyGranted();
            }

        }
    }

    /**
     * Get READ_CONTACTS permission
     */
    public static void getReadContacts(Activity activity, PermissionAskListener listener) {


        if (shouldAskPermission()) {

            int result = ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.READ_CONTACTS);

            if (result != PackageManager.PERMISSION_GRANTED) {

                // If permission denied previously
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.READ_CONTACTS)) {

                    listener.onPreviouslyDenied();

                } else {

                    // Permission denied or first time requested
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            READ_CONTACTS_REQUEST_CODE
                    );

                }

            } else {

                listener.onPreviouslyGranted();
            }

        }
    }

    /**
     * Get READ_SMS permission
     */
    public static void getReadSms(Activity activity, PermissionAskListener listener) {


        if (shouldAskPermission()) {

            int result = ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.READ_SMS);

            if (result != PackageManager.PERMISSION_GRANTED) {

                // If permission denied previously
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.READ_SMS)) {

                    listener.onPreviouslyDenied();

                } else {

                    // Permission denied or first time requested
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.READ_SMS},
                            READ_SMS_REQUEST_CODE
                    );

                }

            } else {

                listener.onPreviouslyGranted();
            }

        }
    }

    /**
     * Get RECEIVE_SMS permission
     */
    public static void getReceiveSms(Activity activity, PermissionAskListener listener) {

        int result = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.RECEIVE_SMS);

        if (result != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.RECEIVE_SMS)) {

                listener.onPreviouslyDenied();

            } else {

                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        RECEIVE_SMS_REQUEST_CODE);
            }

        } else {

            listener.onPreviouslyGranted();
        }
    }

    /**
     * Get Multi permissions
     */
    public static void getMultiPermissions(Activity activity, String[] permissions,
                                           PermissionAskListener listener) {

        if (shouldAskPermission()) {

            for (String permission : permissions) {

                int result = ContextCompat.checkSelfPermission(activity, permission);

                if (result != PackageManager.PERMISSION_GRANTED) {

                    /* If permission denied previously */
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {

                        listener.onPreviouslyDenied();

                    } else {

                        /* Permission denied or first time requested */
                        ActivityCompat.requestPermissions(activity, permissions,
                                READ_PHONE_STATE_REQUEST_CODE);
                    }

                } else {

                    listener.onPreviouslyGranted();
                }
            }

        }
    }


}
