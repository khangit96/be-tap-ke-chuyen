package com.example.khang.betapkechuyen.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khang.betapkechuyen.R;
import com.example.khang.betapkechuyen.activity.TruyenCuaBeActivity;
import com.example.khang.betapkechuyen.model.Sentence;
import com.example.khang.betapkechuyen.model.Story;

import java.util.List;

/**
 * Created by khang on 22/02/2018.
 */

public class SentenceAdapter extends ArrayAdapter<Sentence> {
    Context context;
    List<Sentence> sentenceList;
    int layout;
    public SentenceAdapter(@NonNull Context context, int layout, List<Sentence> sentenceList) {
        super(context, layout, sentenceList);
        this.context=context;
        this.sentenceList=sentenceList;
        this.layout=layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(layout, parent, false);

        final Sentence sentence = sentenceList.get(position);

        TextView tvSentence = (TextView) convertView.findViewById(R.id.tvSentence);

        tvSentence.setText(sentence.content);

        if(position== TruyenCuaBeActivity.SELECTED_POS){
            tvSentence.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            tvSentence.setTextSize(17);
        }
        return convertView;
    }
}
