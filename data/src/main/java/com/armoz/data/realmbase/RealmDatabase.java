package com.armoz.data.realmbase;

import android.content.Context;

import com.armoz.data.R;
import com.armoz.data.entities.QuestionEntity;
import com.armoz.data.entities.StatisticsEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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


        if (getRealmInstance().where(StatisticsEntity.class).findFirst() == null){
            getRealmInstance().executeTransaction(new Realm.Transaction() {

                @Override
                public void execute(Realm realm) {
                    String json = readJsonFromRawResource(R.raw.questions);
                    Type type = new TypeToken<List<QuestionEntity>>() {}.getType();
                    List<QuestionEntity> questionEntityList = new Gson().fromJson(json, type);
                    realm.copyToRealm(questionEntityList);

                    StatisticsEntity statisticsEntity = new StatisticsEntity();
                    statisticsEntity.setId(0);
                    statisticsEntity.setCorrectQuestionsInARow(0);
                    statisticsEntity.setQuestionSeenList(new RealmList<QuestionEntity>());
                    statisticsEntity.setCorrectQuestionList(new RealmList<QuestionEntity>());
                    realm.copyToRealm(statisticsEntity);
                }
            });
        }
    }

    public Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }

    private String readJsonFromRawResource(int rawResourceId) {
        try {
            InputStream is = context.getResources().openRawResource(rawResourceId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            System.out.println(out.toString());
            return out.toString();
        } catch (Exception e) {
            throw new IllegalStateException("Error reading from raw fileId " + rawResourceId);
        }
    }
}
