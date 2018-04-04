package com.taskdata.timetracker.service;

import com.taskdata.timetracker.domain.User;

public class LoginUserResponse {
    private User user;

    public LoginUserResponse() {
    }

    public LoginUserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
