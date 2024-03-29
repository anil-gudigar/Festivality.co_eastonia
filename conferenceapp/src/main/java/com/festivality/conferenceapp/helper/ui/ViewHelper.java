package com.festivality.conferenceapp.helper.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.festivality.conferenceapp.R;

import java.util.Arrays;

/**
 * Created by Anil Gudigar on 09/23/2018.
 */

public class ViewHelper {
    @ColorInt
    public static int getPrimaryDarkColor(@NonNull Context context) {
        return getColorAttr(context, R.attr.colorPrimaryDark);
    }

    @ColorInt public static int getPrimaryColor(@NonNull Context context) {
        return getColorAttr(context, R.attr.colorPrimary);
    }

    @ColorInt public static int getPrimaryTextColor(@NonNull Context context) {
        return getColorAttr(context, android.R.attr.textColorPrimary);
    }

    @ColorInt public static int getSecondaryTextColor(@NonNull Context context) {
        return getColorAttr(context, android.R.attr.textColorSecondary);
    }

    @ColorInt public static int getTertiaryTextColor(@NonNull Context context) {
        return getColorAttr(context, android.R.attr.textColorTertiary);
    }

    @ColorInt public static int getAccentColor(@NonNull Context context) {
        return getColorAttr(context, R.attr.colorAccent);
    }



    @ColorInt private static int getColorAttr(@NonNull Context context, int attr) {
        Resources.Theme theme = context.getTheme();
        TypedArray typedArray = theme.obtainStyledAttributes(new int[]{attr});
        final int color = typedArray.getColor(0, Color.LTGRAY);
        typedArray.recycle();
        return color;
    }

    public static int toPx(@NonNull Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, dp, context.getResources().getDisplayMetrics());
    }

    public static int dpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static void tintDrawable(@NonNull Drawable drawable, @ColorInt int color) {
        drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }



    @NonNull private static Drawable getRippleMask(int color) {
        float[] outerRadii = new float[8];
        Arrays.fill(outerRadii, 3);
        RoundRectShape r = new RoundRectShape(outerRadii, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(r);
        shapeDrawable.getPaint().setColor(color);
        return shapeDrawable;
    }

    @NonNull private static StateListDrawable getStateListDrawable(int normalColor, int pressedColor) {
        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(pressedColor));
        states.addState(new int[]{android.R.attr.state_focused}, new ColorDrawable(pressedColor));
        states.addState(new int[]{android.R.attr.state_activated}, new ColorDrawable(pressedColor));
        states.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(pressedColor));
        states.addState(new int[]{}, new ColorDrawable(normalColor));
        return states;
    }

    public static ColorStateList textSelector(int normalColor, int pressedColor) {
        return new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_activated},
                        new int[]{android.R.attr.state_selected},
                        new int[]{}
                },
                new int[]{
                        pressedColor,
                        pressedColor,
                        pressedColor,
                        pressedColor,
                        normalColor
                }
        );
    }

    private static boolean isTablet(@NonNull Resources resources) {
        return (resources.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    @SuppressWarnings("ConstantConditions") public static boolean isTablet(@NonNull Context context) {
        return context != null && isTablet(context.getResources());
    }

    public static boolean isLandscape(@NonNull Resources resources) {
        return resources.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @NonNull @SuppressWarnings("WeakerAccess") public static Rect getLayoutPosition(@NonNull View view) {
        Rect myViewRect = new Rect();
        view.getGlobalVisibleRect(myViewRect);
        return myViewRect;
    }


    @SuppressWarnings("WeakerAccess") public static void showKeyboard(@NonNull View v, @NonNull Context activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, 0);
    }

    public static void showKeyboard(@NonNull View v) {
        showKeyboard(v, v.getContext());
    }

    public static void hideKeyboard(@NonNull View view) {
        InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @ColorInt public static int generateTextColor(int background) {
        return getContrastColor(background);
    }

    @ColorInt private static int getContrastColor(@ColorInt int color) {
        double a = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return a < 0.5 ? Color.BLACK : Color.WHITE;
    }

    @NonNull public static TextView getTabTextView(@NonNull TabLayout tabs, int tabIndex) {
        return (TextView) (((LinearLayout) ((LinearLayout) tabs.getChildAt(0)).getChildAt(tabIndex)).getChildAt(1));
    }
}
