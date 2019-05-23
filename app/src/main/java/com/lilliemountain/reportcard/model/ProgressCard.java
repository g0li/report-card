package com.lilliemountain.reportcard.model;


import android.os.Parcel;
import android.os.Parcelable;

public class ProgressCard implements Parcelable {

    private String grade;
    private Integer marks;
    private String subject;
    private Integer totalmarks;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProgressCard() {
    }

    /**
     *
     * @param marks
     * @param subject
     * @param totalmarks
     * @param grade
     */
    public ProgressCard(String grade, Integer marks, String subject, Integer totalmarks) {
        super();
        this.grade = grade;
        this.marks = marks;
        this.subject = subject;
        this.totalmarks = totalmarks;
    }

    protected ProgressCard(Parcel in) {
        grade = in.readString();
        if (in.readByte() == 0) {
            marks = null;
        } else {
            marks = in.readInt();
        }
        subject = in.readString();
        if (in.readByte() == 0) {
            totalmarks = null;
        } else {
            totalmarks = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(grade);
        if (marks == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(marks);
        }
        dest.writeString(subject);
        if (totalmarks == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalmarks);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProgressCard> CREATOR = new Creator<ProgressCard>() {
        @Override
        public ProgressCard createFromParcel(Parcel in) {
            return new ProgressCard(in);
        }

        @Override
        public ProgressCard[] newArray(int size) {
            return new ProgressCard[size];
        }
    };

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(Integer totalmarks) {
        this.totalmarks = totalmarks;
    }

}
