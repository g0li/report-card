package com.lilliemountain.reportcard.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class TimeTable implements Parcelable
{

    private String childGrade;
    private String comments;
    private String testName;
    private List<TimeTable_> timeTable = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public TimeTable() {
    }

    /**
     *
     * @param testName
     * @param childGrade
     * @param timeTable
     * @param comments
     */
    public TimeTable(String childGrade, String comments, String testName, List<TimeTable_> timeTable) {
        super();
        this.childGrade = childGrade;
        this.comments = comments;
        this.testName = testName;
        this.timeTable = timeTable;
    }

    protected TimeTable(Parcel in) {
        childGrade = in.readString();
        comments = in.readString();
        testName = in.readString();
        timeTable = in.createTypedArrayList(TimeTable_.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(childGrade);
        dest.writeString(comments);
        dest.writeString(testName);
        dest.writeTypedList(timeTable);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getChildGrade() {
        return childGrade;
    }

    public void setChildGrade(String childGrade) {
        this.childGrade = childGrade;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<TimeTable_> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(List<TimeTable_> timeTable) {
        this.timeTable = timeTable;
    }

}