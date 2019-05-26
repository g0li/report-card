package com.lilliemountain.guardian.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Syllabus_ implements Parcelable
{

    private String subject;
    private String subjectTeacher;
    private String syllabus;
    private String teacherComments;

    /**
     * No args constructor for use in serialization
     *
     */
    public Syllabus_() {
    }

    /**
     *
     * @param subjectTeacher
     * @param subject
     * @param teacherComments
     * @param syllabus
     */
    public Syllabus_(String subject, String subjectTeacher, String syllabus, String teacherComments) {
        super();
        this.subject = subject;
        this.subjectTeacher = subjectTeacher;
        this.syllabus = syllabus;
        this.teacherComments = teacherComments;
    }

    protected Syllabus_(Parcel in) {
        subject = in.readString();
        subjectTeacher = in.readString();
        syllabus = in.readString();
        teacherComments = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(subject);
        dest.writeString(subjectTeacher);
        dest.writeString(syllabus);
        dest.writeString(teacherComments);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Syllabus_> CREATOR = new Creator<Syllabus_>() {
        @Override
        public Syllabus_ createFromParcel(Parcel in) {
            return new Syllabus_(in);
        }

        @Override
        public Syllabus_[] newArray(int size) {
            return new Syllabus_[size];
        }
    };

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getTeacherComments() {
        return teacherComments;
    }

    public void setTeacherComments(String teacherComments) {
        this.teacherComments = teacherComments;
    }

}
