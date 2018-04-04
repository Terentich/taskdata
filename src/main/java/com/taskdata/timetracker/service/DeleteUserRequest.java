package com.taskdata.timetracker.service;

public class DeleteUserRequest {
    private String username;

    public DeleteUserRequest() {
    }

    public DeleteUserRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
