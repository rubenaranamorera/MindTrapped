package com.armoz.mindtrapped.presentation.choosegame.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.armoz.mindtrapped.R;
import com.armoz.mindtrapped.presentation.base.activity.BaseActivity;
import com.armoz.mindtrapped.presentation.choosegame.component.DaggerChooseGameComponent;
import com.armoz.mindtrapped.presentation.choosegame.module.ChooseGameModule;
import com.armoz.mindtrapped.presentation.singlegame.activity.SingleGameActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseGameActivity extends BaseActivity {

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
        ButterKnife.bind(this);
        initializeInjector();
    }

    @OnClick(R.id.choose_game_single_game_button)
    public void goToSingleGame() {
        Intent intent = SingleGameActivity.buildIntent(this);
        this.startActivity(intent);
    }


    private void initializeInjector() {
        DaggerChooseGameComponent.builder()
                .applicationComponent(getApplicationComponent())
                .chooseGameModule(new ChooseGameModule())
                .build()
                .inject(this);
    }
}
