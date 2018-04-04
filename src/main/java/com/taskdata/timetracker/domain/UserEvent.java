package com.taskdata.timetracker.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.StringJoiner;

@Entity
@Table(name = "user_event_table")
public class UserEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private EventType eventType;

    @Column
    private LocalDateTime eventTimestamp;

    @ManyToOne
    private User user;


    public UserEvent() {
    }

    public UserEvent(EventType eventType, LocalDateTime eventTimestamp) {
        this.eventType = eventType;
        this.eventTimestamp = eventTimestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(LocalDateTime eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        StringJoiner message = new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("type=" + eventType)
                .add("timestamp=" + eventTimestamp)
                .add("user=" + user);
        return message.toString();
    }
}
