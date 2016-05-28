package com.armoz.mindtrapped.presentation.choosegame.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.armoz.mindtrapped.presentation.base.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ChooseGameModule {

    public ChooseGameModule() {
    }

    @Provides
    @PerActivity
    SharedPreferences provideSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
  /*
  @Provides
  @PerActivity
  @Named("userList") UseCase provideGetUserListUseCase(GetUserList getUserList) {
    return getUserList;
  }*/

}