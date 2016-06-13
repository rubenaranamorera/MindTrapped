package com.armoz.mindtrapped.presentation.base.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.armoz.mindtrapped.presentation.base.AndroidApplication;
import com.armoz.mindtrapped.presentation.base.component.ApplicationComponent;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends Activity {

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    public void showError(String message){
        showSnackbar(message, Color.RED);
    }

    public void showConfirmation(String message){
        showSnackbar(message, Color.GREEN);
    }

    private void showSnackbar(String message, int green) {
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(green);
        snackbar.show();
    }

    protected void setView(View view){
        this.view = view;
    }

}
