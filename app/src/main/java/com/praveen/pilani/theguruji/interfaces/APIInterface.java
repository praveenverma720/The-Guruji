package com.praveen.pilani.theguruji.interfaces;

import com.praveen.pilani.theguruji.models.education_modal.EducationSubjectResModal;
import com.praveen.pilani.theguruji.models.education_modal.QuestionSetResModal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    //main subjects
    @GET("subjects/")
    Call<EducationSubjectResModal> educationSubjectResModalCall();

    //subcategories of subjects

    @GET("subjectcategories")
    Call<EducationSubjectResModal> educationSubCat(@Query("subject_id") int subject_id);

    //QusetionSet
    @GET("questionsets")
    Call<QuestionSetResModal> questionSet(@Query("subject_category_id") int subject_category_id);

    //QusetionSet
    @GET("questionsets")
    Call<QuestionSetResModal> questionSetFrom(@Query("subject_id") int subject_id);


}