package com.praveen.pilani.theguruji.models;

/**
 * Created by Praveen on 10/08/2018.
 */

public class DataModel {

     String title;
     String content;
     String image;
     String date;


    public DataModel(String title,String content){

        this.title = title;
        this.content = content;


    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}