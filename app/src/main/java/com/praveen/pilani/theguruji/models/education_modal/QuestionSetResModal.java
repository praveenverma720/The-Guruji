package com.praveen.pilani.theguruji.models.education_modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionSetResModal {

    @SerializedName("data")
    @Expose
    private List<QuestionSetDataModal> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<QuestionSetDataModal> getData() {
        return data;
    }

    public void setData(List<QuestionSetDataModal> data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}