package com.taskdata.timetracker.service;

import com.taskdata.timetracker.domain.User;

import java.util.Collection;

public class CreateDailyReportResponse {
    private Collection<User> userEvents;

    public CreateDailyReportResponse() {
    }

    public CreateDailyReportResponse(Collection<User> reportDate) {
        this.userEvents = reportDate;
    }
    
    public Collection<User> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(Collection<User> userEvents) {
        this.userEvents = userEvents;
    }
}
