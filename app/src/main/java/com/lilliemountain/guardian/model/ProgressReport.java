package com.lilliemountain.guardian.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ProgressReport implements Parcelable {

    private String childClass;
    private String childGrade;
    private String grade;
    private Integer grandTotal;
    private String rollNo;
    private String testName;
    private ArrayList<ProgressCard> progressCard = new ArrayList<>();
    private Integer totalMarks;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProgressReport() {
    }

    /**
     *
     * @param childClass
     * @param testName
     * @param totalMarks
     * @param rollNo
     * @param childGrade
     * @param progressCard
     * @param grandTotal
     * @param grade
     */
    public ProgressReport(String childClass, String childGrade, String grade, Integer grandTotal, String rollNo, String testName, ArrayList<ProgressCard> progressCard, Integer totalMarks) {
        super();
        this.childClass = childClass;
        this.childGrade = childGrade;
        this.grade = grade;
        this.grandTotal = grandTotal;
        this.rollNo = rollNo;
        this.testName = testName;
        this.progressCard = progressCard;
        this.totalMarks = totalMarks;
    }

    protected ProgressReport(Parcel in) {
        childClass = in.readString();
        childGrade = in.readString();
        grade = in.readString();
        rollNo = in.readString();
        if (in.readByte() == 0) {
            grandTotal = null;
        } else {
            grandTotal = in.readInt();
        }
        testName = in.readString();
        progressCard = in.createTypedArrayList(ProgressCard.CREATOR);
        if (in.readByte() == 0) {
            totalMarks = null;
        } else {
            totalMarks = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(childClass);
        dest.writeString(childGrade);
        dest.writeString(grade);
        dest.writeString(rollNo);
        if (grandTotal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(grandTotal);
        }
        dest.writeString(testName);
        dest.writeTypedList(progressCard);
        if (totalMarks == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalMarks);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProgressReport> CREATOR = new Creator<ProgressReport>() {
        @Override
        public ProgressReport createFromParcel(Parcel in) {
            return new ProgressReport(in);
        }

        @Override
        public ProgressReport[] newArray(int size) {
            return new ProgressReport[size];
        }
    };

    public String getChildClass() {
        return childClass;
    }

    public void setChildClass(String childClass) {
        this.childClass = childClass;
    }

    public String getChildGrade() {
        return childGrade;
    }

    public void setChildGrade(String childGrade) {
        this.childGrade = childGrade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public ArrayList<ProgressCard> getProgressCard() {
        return progressCard;
    }

    public void setProgressCard(ArrayList<ProgressCard> progressCard) {
        this.progressCard = progressCard;
    }

    public Integer getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }

}
