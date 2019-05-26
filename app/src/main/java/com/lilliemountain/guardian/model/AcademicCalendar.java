package com.lilliemountain.guardian.model;

public class AcademicCalendar {
    String date,eventName;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public AcademicCalendar() {
    }

    public AcademicCalendar(String date, String eventName) {
        this.date = date;
        this.eventName = eventName;
    }
}
