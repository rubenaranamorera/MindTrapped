package com.armoz.mindtrapped.presentation.choosegame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.armoz.mindtrapped.R;
import com.armoz.mindtrapped.presentation.base.activity.BaseActivity;
import com.armoz.mindtrapped.presentation.choosegame.component.DaggerChooseGameComponent;
import com.armoz.mindtrapped.presentation.choosegame.module.ChooseGameModule;
import com.armoz.mindtrapped.presentation.choosegame.presenter.ChooseGamePresenter;
import com.armoz.mindtrapped.presentation.singlegame.activity.SingleGameActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseGameActivity extends BaseActivity {

    @Inject
    ChooseGamePresenter presenter;

    @BindView(R.id.choose_game_single_game_button)
    Button singleGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
        ButterKnife.bind(this);
        initializeInjector();
        presenter.initializeDatabase();
        presenter.setActivity(this);
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

    public void onDatabaseInitialized() {
        singleGameButton.setEnabled(true);
    }
}
