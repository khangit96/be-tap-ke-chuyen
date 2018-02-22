package com.example.khang.betapkechuyen.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
        story= (Story) getIntent().getSerializableExtra("STORY");
        setTitle(story.storyTitle);
    }

    public void initControls(){
        List<Sentence> sentenceList=new ArrayList<>();
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.","l","ff"));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.","l","ff"));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.","l","ff"));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.","l","ff"));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.","l","ff"));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.","l","ff"));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.","l","ff"));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.","l","ff"));
        sentenceList.add(new Sentence("Một hôm hai cha con người nông  dân dắt một con lừa vào chợ. Họ định bán con lừa.","l","ff"));

        lvSentence= (ListView) findViewById(R.id.lvSentence);
        sentenceAdapter=new SentenceAdapter(TruyenCuaBeActivity.this,R.layout.list_sentence,sentenceList);
        lvSentence.setAdapter(sentenceAdapter);
    }
}
