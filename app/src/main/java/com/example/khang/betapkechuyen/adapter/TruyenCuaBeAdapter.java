package com.example.khang.betapkechuyen.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khang.betapkechuyen.R;
import com.example.khang.betapkechuyen.model.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khang on 21/02/2018.
 */

public class TruyenCuaBeAdapter extends ArrayAdapter<Story> {
    Context context;
    List<Story> storyList;
    int layout;
    public TruyenCuaBeAdapter(@NonNull Context context, int layout, List<Story> storyList) {
        super(context, layout, storyList);
        this.context=context;
        this.storyList=storyList;
        this.layout=layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(layout, parent, false);

        final Story story = storyList.get(position);
        TextView tvStoryTitle = (TextView) convertView.findViewById(R.id.tvStoryTitle);
        TextView tvStoryDescription = (TextView) convertView.findViewById(R.id.tvStoryDescription);
        ImageView imgStory = (ImageView) convertView.findViewById(R.id.imgStory);

        tvStoryTitle.setText(story.storyTitle);
        tvStoryDescription.setText(story.storyDescription);
        imgStory.setImageResource(story.storyImgThumbnail);

        return convertView;
    }
}
