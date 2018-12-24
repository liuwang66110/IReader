package com.terrence.iread.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.terrence.iread.IReaderApplication;
import com.terrence.iread.R;

public class SnackBarUtils {

    private Snackbar mSnackbar;

    private SnackBarUtils(Snackbar snackbar) {
        mSnackbar = snackbar;
    }

    public static SnackBarUtils makeShort(View view, String text) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        return new SnackBarUtils(snackbar);
    }

    public static SnackBarUtils makeLong(View view, String text) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        return new SnackBarUtils(snackbar);
    }


    public void show() {
        setSnackBarBackColor();
        mSnackbar.show();
    }

    public void show(String actionText, View.OnClickListener listener) {
        setSnackBarBackColor();
        mSnackbar.setActionTextColor(IReaderApplication.getAppResources().getColor(R.color.white));
        mSnackbar.setAction(actionText, listener).show();
    }


    private View getSnackBarLayout(Snackbar snackbar) {
        if (snackbar != null) {
            return snackbar.getView();
        }
        return null;

    }

    private Snackbar setSnackBarBackColor() {
        View snackBarView = getSnackBarLayout(mSnackbar);
        if (snackBarView != null) {
            snackBarView.setBackgroundColor(ThemeUtils.getThemeColor());
        }
        return mSnackbar;
    }
}