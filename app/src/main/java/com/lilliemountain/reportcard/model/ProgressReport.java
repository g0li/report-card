package com.lilliemountain.reportcard.model;


import android.os.Parcel;
import android.os.Parcelable;

public class ProgressReport implements Parcelable {

    private String testName;
    private String reportcard;
    private Integer totalMarks;
    private Integer grandTotal;
    private String grade;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProgressReport() {
    }

    /**
     *
     * @param reportcard
     * @param testName
     * @param totalMarks
     * @param grandTotal
     * @param grade
     */
    public ProgressReport(String testName, String reportcard, Integer totalMarks, Integer grandTotal, String grade) {
        super();
        this.testName = testName;
        this.reportcard = reportcard;
        this.totalMarks = totalMarks;
        this.grandTotal = grandTotal;
        this.grade = grade;
    }

    protected ProgressReport(Parcel in) {
        testName = in.readString();
        reportcard = in.readString();
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

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getReportcard() {
        return reportcard;
    }

    public void setReportcard(String reportcard) {
        this.reportcard = reportcard;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(testName);
        dest.writeString(reportcard);
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
}
