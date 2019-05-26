package com.lilliemountain.guardian.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class Syllabus implements Parcelable {
    String testName, childGrade  ;
    ArrayList<DataSnapshot> syllabusinfo;

    protected Syllabus(Parcel in) {
        testName = in.readString();
        childGrade = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(testName);
        dest.writeString(childGrade);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getChildGrade() {
        return childGrade;
    }

    public void setChildGrade(String childGrade) {
        this.childGrade = childGrade;
    }

    public ArrayList<DataSnapshot> getSyllabusinfo() {
        return syllabusinfo;
    }

    public void setSyllabusinfo(ArrayList<DataSnapshot> syllabusinfo) {
        this.syllabusinfo = syllabusinfo;
    }

    public Syllabus() {
    }

    public Syllabus(String testName, String childGrade, ArrayList<DataSnapshot> syllabusinfo) {
        this.testName = testName;
        this.childGrade = childGrade;
        this.syllabusinfo = syllabusinfo;
    }
}
