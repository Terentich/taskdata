package com.taskdata.timetracker.service;

import com.taskdata.timetracker.domain.User;

public class DeleteUserResponse {
    private User user;

    public DeleteUserResponse() {
    }

    public DeleteUserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
