package com.praveen.pilani.theguruji.models.education_modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EducationSubjectsListModal {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("is_sub_category")
    @Expose
    private boolean is_sub_category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isIs_sub_category() {
        return is_sub_category;
    }

    public void setIs_sub_category(boolean is_sub_category) {
        this.is_sub_category = is_sub_category;
    }
}