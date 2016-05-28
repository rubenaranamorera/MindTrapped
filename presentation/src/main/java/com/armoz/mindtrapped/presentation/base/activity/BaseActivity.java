package com.armoz.mindtrapped.presentation.base.activity;

import android.app.Activity;
import android.os.Bundle;

import com.armoz.mindtrapped.presentation.base.AndroidApplication;
import com.armoz.mindtrapped.presentation.base.component.ApplicationComponent;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getApplicationComponent().inject(this);
  }

  /**
   * Get the Main Application component for dependency injection.
   */
  protected ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication)getApplication()).getApplicationComponent();
  }
}
