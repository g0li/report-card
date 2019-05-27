package com.lilliemountain.guardian.model;

public class Messages {
    String message;
    String timestamp;

    public Messages(String message, String timestamp, String email, boolean seen, boolean mymessage) {
        this.message = message;
        this.timestamp = timestamp;
        this.email = email;
        this.seen = seen;
        this.mymessage = mymessage;
    }

    String email;
    boolean seen,mymessage;

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

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
