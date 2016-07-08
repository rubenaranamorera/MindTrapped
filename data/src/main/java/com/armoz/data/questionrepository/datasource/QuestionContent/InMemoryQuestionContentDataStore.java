package com.armoz.data.questionrepository.datasource.QuestionContent;

import android.content.Context;

import com.armoz.data.R;
import com.armoz.data.entities.QuestionContentEntity;
import com.armoz.data.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class InMemoryQuestionContentDataStore implements QuestionContentDataStore {

    private HashMap<Integer, QuestionContentEntity> questionContentMap;
    private Context context;

    public InMemoryQuestionContentDataStore(Context context) {
        this.context = context;
    }

    @Override
    public QuestionContentEntity getQuestionContent(int questionId) {
        if (questionContentMap == null){
            initializeQuestionContentMap();
        }
        return questionContentMap.get(questionId);
    }

    private void initializeQuestionContentMap() {
        String json = JsonUtils.readJsonFromRawResource(context, R.raw.questions);
        Type type = new TypeToken<List<QuestionContentEntity>>() {}.getType();
        List<QuestionContentEntity> questionContentEntities = new Gson().fromJson(json, type);

        questionContentMap = new HashMap<>();
        for (QuestionContentEntity questionContentEntity: questionContentEntities){
            questionContentMap.put(questionContentEntity.getId(), questionContentEntity);
        }
    }
}