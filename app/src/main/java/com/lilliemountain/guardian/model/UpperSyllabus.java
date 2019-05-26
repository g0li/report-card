package com.lilliemountain.guardian.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class UpperSyllabus implements Parcelable {
    Syllabus syllabus;
    ArrayList<Syllabus_>  list=new ArrayList<>();

    protected UpperSyllabus(Parcel in) {
        syllabus = in.readParcelable(Syllabus.class.getClassLoader());
        list = in.createTypedArrayList(Syllabus_.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(syllabus, flags);
        dest.writeTypedList(list);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UpperSyllabus> CREATOR = new Creator<UpperSyllabus>() {
        @Override
        public UpperSyllabus createFromParcel(Parcel in) {
            return new UpperSyllabus(in);
        }

        @Override
        public UpperSyllabus[] newArray(int size) {
            return new UpperSyllabus[size];
        }
    };

    public Syllabus getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(Syllabus syllabus) {
        this.syllabus = syllabus;
    }

    public ArrayList<Syllabus_> getList() {
        return list;
    }

    public void setList(ArrayList<Syllabus_> list) {
        this.list = list;
    }

    public UpperSyllabus() {
    }

    public UpperSyllabus(Syllabus syllabus, ArrayList<Syllabus_> list) {
        this.syllabus = syllabus;
        this.list = list;
    }
}
