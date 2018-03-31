package com.taskdata.timetracker.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.StringJoiner;

@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Collection<UserEvent> userEvents;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<UserEvent> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(Collection<UserEvent> userEvents) {
        this.userEvents = userEvents;
    }

    @Override
    public String toString() {
        StringJoiner message = new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username=" + username);
        return message.toString();
    }
}
