package com.taskdata.timetracker.service;

import java.util.Date;

public class LogoutUserRequest {
    private String username;
    private Date eventTimestamp;

    public LogoutUserRequest() {
    }

    public LogoutUserRequest(String username, Date eventTimestamp) {
        this.username = username;
        this.eventTimestamp = eventTimestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Date eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }
}
