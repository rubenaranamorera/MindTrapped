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

    private Context context;
    private int resource;
    private List<Statistics> statistics;

    public RankingsAdapter(Context context, int resource, List<Statistics> statistics) {
        super(context, resource, statistics);
        this.context = context;
        this.resource = resource;
        this.statistics = statistics;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rootView = convertView;

        if (rootView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(resource, null);
        }

        Statistics statistics = getItem(position);
        TextView titleView = (TextView) rootView.findViewById(R.id.rankings_correct_questions_size);
        titleView.setText(statistics.getCorrectQuestions());

        return rootView;
    }
}
