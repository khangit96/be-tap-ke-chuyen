package com.example.khang.betapkechuyen.activity;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khang.betapkechuyen.R;
import com.example.khang.betapkechuyen.adapter.SentenceAdapter;
import com.example.khang.betapkechuyen.model.Sentence;
import com.example.khang.betapkechuyen.model.Story;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    List<Sentence> sentenceList;
    FloatingActionButton fab;
    boolean check = false;
    DatabaseReference mDatabse;
    int pos;

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
        sentenceList = story.sentenceList;
        setTitle(story.storyTitle);
    }

    public void initControls() {
        imgSentence = (ImageView) findViewById(R.id.imgSentence);
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
                        SELECTED_POS = COUNT_SENTENCE;

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

        if (story.storyTitle.equals("Hai cha con và con lừa")) {
            pos = 1;
        } else {
            pos = 2;
        }

        mDatabse = FirebaseDatabase.getInstance().getReference();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    handler.removeCallbacksAndMessages(null);
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }

                if (check) {
                    check = false;
                    mDatabse.child("BeTapKeChuyen/" + pos + "/start").setValue(false);
                    return;
                }

                check = true;
                //Set firebase falseos
                mDatabse.child("BeTapKeChuyen/" + pos + "/start").setValue(true);
            }
        });
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
