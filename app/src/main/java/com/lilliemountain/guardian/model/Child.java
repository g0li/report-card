package com.lilliemountain.guardian.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Child implements Parcelable {

    private String childAge;
    private String childClass;
    private String childGender;
    private String childGrade;
    private String childName;
    private String parentEmail;
    private String rollNo;

    public Child() {}

    protected Child(Parcel in) {
        childAge = in.readString();
        childClass = in.readString();
        childGender = in.readString();
        childGrade = in.readString();
        childName = in.readString();
        parentEmail = in.readString();
        rollNo = in.readString();
    }

    public static final Creator<Child> CREATOR = new Creator<Child>() {
        @Override
        public Child createFromParcel(Parcel in) {
            return new Child(in);
        }

        @Override
        public Child[] newArray(int size) {
            return new Child[size];
        }
    };

    public String getChildAge() {
        return childAge;
    }

    public void setChildAge(String childAge) {
        this.childAge = childAge;
    }

    public String getChildClass() {
        return childClass;
    }

    public void setChildClass(String childClass) {
        this.childClass = childClass;
    }

    public String getChildGender() {
        return childGender;
    }

    public void setChildGender(String childGender) {
        this.childGender = childGender;
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

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(childAge);
        dest.writeString(childClass);
        dest.writeString(childGender);
        dest.writeString(childGrade);
        dest.writeString(childName);
        dest.writeString(parentEmail);
        dest.writeString(rollNo);
    }
}
