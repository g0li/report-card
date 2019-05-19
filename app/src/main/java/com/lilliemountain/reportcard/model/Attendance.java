package com.lilliemountain.reportcard.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Attendance implements Parcelable {

    private String attendance;
    private String childClass;
    private String childGrade;
    private String childName;
    private String dateOfAttendance;
    private String parentEmail;
    private Integer rollNo;

    /**
     * No args constructor for use in serialization
     *
     */
    public Attendance() {
    }

    /**
     *
     * @param childClass
     * @param rollNo
     * @param parentEmail
     * @param childName
     * @param childGrade
     * @param dateOfAttendance
     * @param attendance
     */
    public Attendance(String attendance, String childClass, String childGrade, String childName, String dateOfAttendance, String parentEmail, Integer rollNo) {
        super();
        this.attendance = attendance;
        this.childClass = childClass;
        this.childGrade = childGrade;
        this.childName = childName;
        this.dateOfAttendance = dateOfAttendance;
        this.parentEmail = parentEmail;
        this.rollNo = rollNo;
    }

    protected Attendance(Parcel in) {
        attendance = in.readString();
        childClass = in.readString();
        childGrade = in.readString();
        childName = in.readString();
        dateOfAttendance = in.readString();
        parentEmail = in.readString();
        if (in.readByte() == 0) {
            rollNo = null;
        } else {
            rollNo = in.readInt();
        }
    }

    public static final Creator<Attendance> CREATOR = new Creator<Attendance>() {
        @Override
        public Attendance createFromParcel(Parcel in) {
            return new Attendance(in);
        }

        @Override
        public Attendance[] newArray(int size) {
            return new Attendance[size];
        }
    };

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

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

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getDateOfAttendance() {
        return dateOfAttendance;
    }

    public void setDateOfAttendance(String dateOfAttendance) {
        this.dateOfAttendance = dateOfAttendance;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(attendance);
        dest.writeString(childClass);
        dest.writeString(childGrade);
        dest.writeString(childName);
        dest.writeString(dateOfAttendance);
        dest.writeString(parentEmail);
        if (rollNo == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(rollNo);
        }
    }
}
