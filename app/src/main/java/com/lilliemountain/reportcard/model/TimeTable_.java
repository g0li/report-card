package com.lilliemountain.reportcard.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TimeTable_ implements Parcelable
{

    private String date;
    private String subject;

    /**
     * No args constructor for use in serialization
     *
     */
    public TimeTable_() {
    }

    /**
     *
     * @param subject
     * @param date
     */
    public TimeTable_(String date, String subject) {
        super();
        this.date = date;
        this.subject = subject;
    }

    protected TimeTable_(Parcel in) {
        date = in.readString();
        subject = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(subject);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TimeTable_> CREATOR = new Creator<TimeTable_>() {
        @Override
        public TimeTable_ createFromParcel(Parcel in) {
            return new TimeTable_(in);
        }

        @Override
        public TimeTable_[] newArray(int size) {
            return new TimeTable_[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
