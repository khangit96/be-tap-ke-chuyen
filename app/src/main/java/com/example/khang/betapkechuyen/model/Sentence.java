package com.example.khang.betapkechuyen.model;

import java.io.Serializable;

/**
 * Created by Administrator on 6/12/2017.
 */

public class Sentence implements Serializable {
    public String content;
    public String imgResource;
    public String soundResource;

    public Sentence() {

    }

    public Sentence(String content, String imgResource, String soundResource) {
        this.content = content;
        this.soundResource = soundResource;
        this.imgResource = imgResource;
    }
}
