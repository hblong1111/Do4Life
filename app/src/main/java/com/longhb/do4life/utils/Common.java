package com.longhb.do4life.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Common {
    public static final String BASE_URL = "https://dofolife.herokuapp.com";
    public static final String MY_PREFS_NAME = "data";
    public static final String KEY_PREFS_USERNAME = "username";
    public static final String KEY_PREFS_PASSWORD = "pass";
    public static final String CODE_PUT_ID_ACCOUNT = "id_acc";
    public static final String KEY_ID_ACC = "KEY_ID_ACC";
    public static final String KEY_CHECKED_ACC = "KEY_CHECKED_ACC";

    public static AlertDialog showDialogAlert(Context context, String mess, String textBtn, DialogInterface.OnClickListener callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông Báo");
        builder.setMessage(mess);
        builder.setNegativeButton(textBtn, callback);
        builder.create();
        return builder.show();
    }

    public static ProgressDialog buildDialogLoading(Context context, String title, CharSequence mess){
        ProgressDialog  dialog=new ProgressDialog(context);
        dialog.setTitle(title);
        dialog.setMessage(mess);
        return dialog;
    }

}
