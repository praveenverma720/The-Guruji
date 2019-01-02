package com.praveen.pilani.theguruji.models.education_modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EducationSubjectResModal {

    @SerializedName("data")
    @Expose
    private List<EducationSubjectsListModal> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<EducationSubjectsListModal> getData() {
        return data;
    }

    public void setData(List<EducationSubjectsListModal> data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
