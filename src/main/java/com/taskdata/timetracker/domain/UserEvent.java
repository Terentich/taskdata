package com.taskdata.timetracker.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.StringJoiner;

@Entity
@Table(name = "user_event_table")
public class UserEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private EventType eventType;

    private ZonedDateTime eventTimestamp;

    public UserEvent() {
    }

    public UserEvent(EventType eventType, ZonedDateTime eventTimestamp) {
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

    public ZonedDateTime getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(ZonedDateTime eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    @Override
    public String toString() {
        StringJoiner message = new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("type=" + eventType)
                .add("timestamp=" + eventTimestamp);
        return message.toString();
    }
}
