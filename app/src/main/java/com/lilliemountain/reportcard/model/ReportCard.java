package com.lilliemountain.reportcard.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ReportCard implements Parcelable {
    String subject,marks,totalmarks,grade;

    public ReportCard() {
    }

    protected ReportCard(Parcel in) {
        subject = in.readString();
        marks = in.readString();
        totalmarks = in.readString();
        grade = in.readString();
    }

    public static final Creator<ReportCard> CREATOR = new Creator<ReportCard>() {
        @Override
        public ReportCard createFromParcel(Parcel in) {
            return new ReportCard(in);
        }

        @Override
        public ReportCard[] newArray(int size) {
            return new ReportCard[size];
        }
    };

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(String totalmarks) {
        this.totalmarks = totalmarks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public ReportCard(String subject, String marks, String totalmarks, String grade) {
        this.subject = subject;
        this.marks = marks;
        this.totalmarks = totalmarks;
        this.grade = grade;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(subject);
        dest.writeString(marks);
        dest.writeString(totalmarks);
        dest.writeString(grade);
    }
}
