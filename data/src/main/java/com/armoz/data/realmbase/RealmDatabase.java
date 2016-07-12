package com.armoz.data.realmbase;

import android.content.Context;

import com.armoz.data.R;
import com.armoz.data.entities.QuestionEntity;
import com.armoz.data.entities.StatisticsEntity;
import com.armoz.data.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

@Singleton
public class RealmDatabase {

    @Inject
    Context context;

    RealmConfiguration realmConfiguration;

    public RealmDatabase(Context context) {
        this.context = context;
        setup();
    }

    public void setup() {
        if (realmConfiguration == null) {
            realmConfiguration = new RealmConfiguration.Builder(context).build();
            Realm.setDefaultConfiguration(realmConfiguration);
            initialize();
        } else {
            throw new IllegalStateException("database already configured");
        }
    }

    private void initialize() {
        if (getRealmInstance().where(QuestionEntity.class).findFirst() == null){
            getRealmInstance().executeTransaction(new Realm.Transaction() {

                @Override
                public void execute(Realm realm) {
                    String json = JsonUtils.readJsonFromRawResource(context, R.raw.questions);
                    Type type = new TypeToken<List<QuestionEntity>>() {}.getType();
                    List<QuestionEntity> questionEntityList = new Gson().fromJson(json, type);
                    realm.copyToRealm(questionEntityList);
                }
            });
        }
    }

    public Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }
}
