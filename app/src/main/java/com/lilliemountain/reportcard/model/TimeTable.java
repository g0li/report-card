package com.lilliemountain.reportcard.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TimeTable implements Parcelable {
    String examName, timetable;

    public TimeTable(String testName, String timetable) {
        this.examName = testName;
        this.timetable = timetable;
    }

    public TimeTable() {}

    protected TimeTable(Parcel in) {
        examName = in.readString();
        timetable = in.readString();
    }

    public static final Creator<TimeTable> CREATOR = new Creator<TimeTable>() {
        @Override
        public TimeTable createFromParcel(Parcel in) {
            return new TimeTable(in);
        }

        @Override
        public TimeTable[] newArray(int size) {
            return new TimeTable[size];
        }
    };

    public String getExamName() {
        return examName;
    }

    public void setTestName(String testName) {
        this.examName = testName;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(examName);
        dest.writeString(timetable);
    }
}
