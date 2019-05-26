package com.lilliemountain.guardian.model;

public class Messages {
    String message,timestamp;
    boolean mymessage;

    public Messages() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isMymessage() {
        return mymessage;
    }

    public void setMymessage(boolean mymessage) {
        this.mymessage = mymessage;
    }

    public Messages(String message, String timestamp, boolean mymessage) {
        this.message = message;
        this.timestamp = timestamp;
        this.mymessage = mymessage;
    }
}
