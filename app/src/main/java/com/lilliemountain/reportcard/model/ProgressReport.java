package com.lilliemountain.reportcard.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProgressReport implements Parcelable{
    private String testName;
    private ArrayList<ReportCard> reportcard=new ArrayList<>();
    private Integer totalMarks;
    private Integer grandTotal;
    private String grade;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProgressReport() {
    }


    protected ProgressReport(Parcel in) {
        testName = in.readString();
        reportcard = in.createTypedArrayList(ReportCard.CREATOR);
        if (in.readByte() == 0) {
            totalMarks = null;
        } else {
            totalMarks = in.readInt();
        }
        if (in.readByte() == 0) {
            grandTotal = null;
        } else {
            grandTotal = in.readInt();
        }
        grade = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(testName);
        dest.writeTypedList(reportcard);
        if (totalMarks == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalMarks);
        }
        if (grandTotal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(grandTotal);
        }
        dest.writeString(grade);
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

    public String getTestName() {
        return testName;
    }

    public void setReportCard(ArrayList<ReportCard> reportcard) {
        this.reportcard = reportcard;
    }

    public ArrayList<ReportCard> getReportCard() {
        return reportcard;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }


    public Integer getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Integer getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public ProgressReport(String testName, ArrayList<ReportCard> reportcard, Integer totalMarks, Integer grandTotal, String grade) {
        this.testName = testName;
        this.reportcard = reportcard;
        this.totalMarks = totalMarks;
        this.grandTotal = grandTotal;
        this.grade = grade;
    }
}
