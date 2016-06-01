package com.armoz.data.repository.datasource;

import android.content.Context;

import com.armoz.data.R;
import com.armoz.data.entity.QuestionEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.Observable;

public class RealmQuestionDataStore implements QuestionDataStore {

    private Context context;

    public RealmQuestionDataStore(Context context) {
        this.context = context;
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).
                initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        String json = readJsonFromRawResource(R.raw.questions);
                        Type type = new TypeToken<List<QuestionEntity>>() {}.getType();
                        List<QuestionEntity> questionEntityList = new Gson().fromJson(json, type);
                        realm.copyToRealm(questionEntityList);
                    }
                })
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    @Override
    public Observable<QuestionEntity> questionEntity() {
        Realm realm = Realm.getDefaultInstance();
        return Observable.just(realm.copyFromRealm(realm.where(QuestionEntity.class).findFirst()));
    }

    @Override
    public Observable<Boolean> initializeDatabase() {
        /*String json = readJsonFromRawResource(R.raw.questions);
        Type type = new TypeToken<List<QuestionEntity>>() {}.getType();
        List<QuestionEntity> questionEntityList = new Gson().fromJson(json, type);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(questionEntityList);
        realm.commitTransaction();*/
        return Observable.just(true);
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
            return out.toString();
        } catch (Exception e) {
            throw new IllegalStateException("Error reading from raw fileId " + rawResourceId);
        }
    }
}
