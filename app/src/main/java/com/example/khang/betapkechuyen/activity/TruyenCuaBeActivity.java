package com.example.khang.betapkechuyen.activity;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.khang.betapkechuyen.R;
import com.example.khang.betapkechuyen.adapter.SentenceAdapter;
import com.example.khang.betapkechuyen.model.Sentence;
import com.example.khang.betapkechuyen.model.Story;

import java.util.ArrayList;
import java.util.List;

public class TruyenCuaBeActivity extends AppCompatActivity {
    Toolbar toolbar;
    Story story;
    ListView lvSentence;
    SentenceAdapter sentenceAdapter;
    MediaPlayer mediaPlayer;
    Handler handler;
    Runnable runnable;
    int COUNT_SENTENCE = 0;
    public static int SELECTED_POS = -1;
    ImageView imgSentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen_cua_be);

        initToolbar();
        initControls();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        story = (Story) getIntent().getSerializableExtra("STORY");
        setTitle(story.storyTitle);
    }

    public void initControls() {
        final List<Sentence> sentenceList = new ArrayList<>();
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.", R.drawable.haichaconvaconlua_1, R.raw.haichaconlua_1));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.", R.drawable.haichaconvaconlua_2, R.raw.haichaconlua_2));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.", R.drawable.haichaconvaconlua_3, R.raw.haichaconlua_3));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.", R.drawable.haichaconvaconlua_4, R.raw.haichaconlua_4));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.", R.drawable.haichaconvaconlua_5, R.raw.haichaconlua_5));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.", R.drawable.haichaconvaconlua_6, R.raw.haichaconlua_6));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.", R.drawable.haichaconvaconlua_7, R.raw.haichaconlua_7));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.", R.drawable.haichaconvaconlua_7, R.raw.haichaconlua_8));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.", R.drawable.haichaconvaconlua_7, R.raw.haichaconlua_9));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.", R.drawable.haichaconvaconlua_7, R.raw.haichaconlua_10));

        imgSentence= (ImageView) findViewById(R.id.imgSentence);
        imgSentence.setImageResource(sentenceList.get(0).imgResource);
        lvSentence = (ListView) findViewById(R.id.lvSentence);
        sentenceAdapter = new SentenceAdapter(TruyenCuaBeActivity.this, R.layout.list_sentence, sentenceList);
        lvSentence.setAdapter(sentenceAdapter);
        mediaPlayer = MediaPlayer.create(TruyenCuaBeActivity.this, sentenceList.get(0).soundResource);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                if (!mediaPlayer.isPlaying()) {
                    if (COUNT_SENTENCE <= sentenceList.size() - 1) {
                        imgSentence.setImageResource(sentenceList.get(COUNT_SENTENCE).imgResource);
                        mediaPlayer = MediaPlayer.create(TruyenCuaBeActivity.this, sentenceList.get(COUNT_SENTENCE).soundResource);
                        mediaPlayer.start();
                        sentenceAdapter.notifyDataSetChanged();
                        SELECTED_POS=COUNT_SENTENCE;

                        lvSentence.post(new Runnable() {
                            @Override
                            public void run() {
                                lvSentence.setSelection(COUNT_SENTENCE - 1);
                            }
                        });
                        COUNT_SENTENCE++;
                    }
                }
            }
        };

        runOnUiThread(runnable);
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            handler.removeCallbacksAndMessages(null);
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}
