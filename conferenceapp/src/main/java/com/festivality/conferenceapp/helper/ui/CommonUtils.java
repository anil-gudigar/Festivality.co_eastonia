package com.festivality.conferenceapp.helper.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Point;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by ankumar on 11/15/2017.
 */

public class CommonUtils {
    public CommonUtils() {
    }

    public static Activity getActivityFromContext(@NonNull Context context) {
        while(context instanceof ContextWrapper) {
            if(context instanceof Activity) {
                return (Activity)context;
            }

            context = ((ContextWrapper)context).getBaseContext();
        }

        return null;
    }

    public static Point getScreenSize(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return new Point(dm.widthPixels, dm.heightPixels);
    }

    public static int getStatusBarHeight(Context activity) {
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0?activity.getResources().getDimensionPixelSize(resourceId):0;
    }

    public static void hideSoftKeyboard(Context context) {
        try {
            Activity activity = getActivityFromContext(context);
            if(activity != null) {
                hideSoftKeyboard(activity);
            }
        } catch (Exception var2) {
        }

    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            View view = activity.getCurrentFocus();
            if(view != null) {
                InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception var3) {
        }

    }

    public static void showSoftKeyboard(Context context, EditText edt) {
        edt.setEnabled(true);
        edt.setFocusable(true);
        edt.setFocusableInTouchMode(true);
        edt.requestFocus();
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edt, 1);
        edt.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, 0.0F, 0.0F, 0));
        edt.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, 0.0F, 0.0F, 0));
    }

    @TargetApi(21)
    public static void changeStatusBarColor(Context activity, int color) {
        if(Build.VERSION.SDK_INT >= 21) {
            Window window = ((Activity)activity).getWindow();
            window.clearFlags(67108864);
            window.addFlags(-2147483648);
            window.setStatusBarColor(color);
        }

    }
}

