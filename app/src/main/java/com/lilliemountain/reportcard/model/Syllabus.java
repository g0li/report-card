package com.lilliemountain.reportcard.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Syllabus implements Parcelable {
    String testName,syllabus;

    public Syllabus(String testName, String syllabus) {
        this.testName = testName;
        this.syllabus = syllabus;
    }

    public Syllabus() {}

    protected Syllabus(Parcel in) {
        testName = in.readString();
        syllabus = in.readString();
    }

    public static final Creator<Syllabus> CREATOR = new Creator<Syllabus>() {
        @Override
        public Syllabus createFromParcel(Parcel in) {
            return new Syllabus(in);
        }

        @Override
        public Syllabus[] newArray(int size) {
            return new Syllabus[size];
        }
    };

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(testName);
        dest.writeString(syllabus);
    }
}
