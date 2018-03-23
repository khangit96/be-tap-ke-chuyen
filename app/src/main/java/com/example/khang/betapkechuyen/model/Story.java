package com.example.khang.betapkechuyen.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by khang on 21/02/2018.
 */

public class Story implements Serializable {
    public String storyDescription;
    public int storyImgThumbnail;
    public String storyTitle;
    public List<Sentence> sentenceList;

    public Story() {

    }

    public Story(String storyDescription, int storyImgThumbnail, String storyTitle, List<Sentence> sentenceList) {
        this.storyDescription = storyDescription;
        this.storyImgThumbnail = storyImgThumbnail;
        this.storyTitle = storyTitle;
        this.sentenceList = sentenceList;
    }
}
