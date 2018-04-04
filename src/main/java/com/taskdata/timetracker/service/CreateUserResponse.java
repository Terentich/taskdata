package com.taskdata.timetracker.service;

import com.taskdata.timetracker.domain.User;

public class CreateUserResponse {
    private User user;

    public CreateUserResponse() {
    }

    public CreateUserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
