package com.armoz.mindtrapped.presentation.statistics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.armoz.mindtrapped.R;
import com.mindtrapped.model.Statistics;

import java.util.List;

public class RankingsAdapter extends ArrayAdapter<Statistics> {

    public RankingsAdapter(Context context, List<Statistics> statistics) {
        super(context, 0, statistics);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Statistics statistics = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rankings_list_item, parent, false);
        }

        TextView titleView = (TextView) convertView.findViewById(R.id.rankings_correct_questions_size);
        titleView.setText(Integer.toString(statistics.getCorrectQuestions()));

        return convertView;
    }
}
