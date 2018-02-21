package com.example.khang.betapkechuyen.model;

/**
 * Created by khang on 21/02/2018.
 */

public class Story {
    public String storyDescription;
    public int storyImgThumbnail;
    public String storyTitle;

    public Story(){

    }

    public Story(String storyDescription, int storyImgThumbnail, String storyTitle) {
        this.storyDescription = storyDescription;
        this.storyImgThumbnail = storyImgThumbnail;
        this.storyTitle = storyTitle;
    }
}
