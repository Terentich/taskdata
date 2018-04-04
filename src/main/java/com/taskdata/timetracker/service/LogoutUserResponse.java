package com.taskdata.timetracker.service;

import com.taskdata.timetracker.domain.User;

public class LogoutUserResponse {
    private User user;

    public LogoutUserResponse() {
    }

    public LogoutUserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
