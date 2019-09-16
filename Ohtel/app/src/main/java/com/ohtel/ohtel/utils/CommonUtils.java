package com.ohtel.ohtel.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ohtel.ohtel.R;

public class CommonUtils {

    private static ProgressDialog dialog;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public static void showProgressBar(Context context){
        dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.setMessage(context.getString(R.string.loading));
        dialog.show();
    }

    public static void dismissProgressDialog(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
