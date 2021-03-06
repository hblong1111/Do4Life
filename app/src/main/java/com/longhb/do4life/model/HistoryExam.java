package com.longhb.do4life.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryExam {
    @SerializedName("stt_history")
    @Expose
    public String STT_history;
    @SerializedName("Name_Exam")
    @Expose
    public String name_Exam;
    @SerializedName("Day_History")
    @Expose
    public String day_history;

    public HistoryExam(String STT_history, String name_Exam, String day_history) {
        this.STT_history = STT_history;
        this.name_Exam = name_Exam;
        this.day_history = day_history;
    }
    public HistoryExam() {
    }
}
