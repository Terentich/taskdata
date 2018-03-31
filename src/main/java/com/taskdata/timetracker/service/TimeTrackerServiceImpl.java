package com.taskdata.timetracker.service;

import com.taskdata.timetracker.domain.EventType;
import com.taskdata.timetracker.domain.User;
import com.taskdata.timetracker.domain.UserEvent;
import com.taskdata.timetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@WebService
public class TimeTrackerServiceImpl implements TimeTrackerService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(String username) {
        if (userRepository.findByUsername(username) != null) {
            throw new EntityExistsException("The user '" + username + "' already exists");
        }

        return userRepository.save(new User(username));
    }

    @Override
    public User deleteUser(String username) {
        User user = getUser(username);
        userRepository.delete(user);
        return user;
    }

    @Override
    public User loginUser(String username, Date eventTimestamp) {
        User user = getUser(username);
        UserEvent userEvent = new UserEvent(EventType.LOGIN, eventTimestamp.toInstant().atZone(ZoneId.systemDefault()));
        user.getUserEvents().add(userEvent);
        userRepository.save(user);
        return user;
    }

    @Override
    public User logoutUser(String username, Date eventTimestamp) {
        User user = getUser(username);
        UserEvent userEvent = new UserEvent(EventType.LOGOUT, eventTimestamp.toInstant().atZone(ZoneId.systemDefault()));
        user.getUserEvents().add(userEvent);
        userRepository.save(user);
        return user;
    }

    @Override
    public Collection<User> createDailyReport(Date date) {
        return null;
    }

    @Override
    public Collection<UserEvent> createUserReport(String username, Date startDate, Date endDate) {
        User user = getUser(username);

        return user.getUserEvents().stream()
                .filter(e -> e.getEventTimestamp().isAfter(startDate.toInstant().atZone(ZoneId.systemDefault()))
                        && e.getEventTimestamp().isBefore(endDate.toInstant().atZone(ZoneId.systemDefault())))
                .collect(Collectors.toList());
    }

    private User getUser(String username) {
        return Objects.requireNonNull(userRepository.findByUsername(username), () -> {
            throw new EntityNotFoundException("Unable to find out the user '" + username + "'");
        });
    }
}
