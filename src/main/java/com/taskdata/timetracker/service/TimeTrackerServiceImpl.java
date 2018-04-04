package com.taskdata.timetracker.service;

import com.taskdata.timetracker.domain.EventType;
import com.taskdata.timetracker.domain.User;
import com.taskdata.timetracker.domain.UserEvent;
import com.taskdata.timetracker.repository.UserEventRepository;
import com.taskdata.timetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TimeTrackerServiceImpl implements TimeTrackerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserEventRepository userEventRepository;

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        String username = createUserRequest.getUsername();

        if (userRepository.findByUsername(username) != null) {
            throw new EntityExistsException("The user '" + username + "' already exists");
        }

        return userRepository.save(new User(username));
    }

    @Override
    public User deleteUser(DeleteUserRequest deleteUserRequest) {
        String username = deleteUserRequest.getUsername();

        User user = getUser(username);
        userRepository.delete(user);

        return user;
    }

    @Override
    public User loginUser(LoginUserRequest loginUserRequest) {
        User user = getUser(loginUserRequest.getUsername());
        UserEvent userEvent = new UserEvent(EventType.LOGIN, loginUserRequest.getEventTimestamp()
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        user.getUserEvents().add(userEvent);
        userRepository.save(user);
        return user;
    }

    @Override
    public User logoutUser(LogoutUserRequest logoutUserRequest) {
        String username = logoutUserRequest.getUsername();
        User user = getUser(username);

        UserEvent userEvent = new UserEvent(EventType.LOGOUT, logoutUserRequest.getEventTimestamp()
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        user.getUserEvents().add(userEvent);
        userRepository.save(user);

        return user;
    }

    @Override
    public Collection<User> createDailyReport(CreateDailyReportRequest createDailyReportRequest) {
        LocalDate reportDay = createDailyReportRequest.getReportDate()
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        List<UserEvent> collect = userRepository.findAll().stream()
                .flatMap(u -> u.getUserEvents().stream())
                .filter(e -> e.getEventTimestamp().toLocalDate().equals(reportDay))
                .collect(Collectors.toList());
        System.out.printf("COLLECTED: \n");
        System.out.println(collect.stream().map(Object::toString).collect(Collectors.joining("\n")));

        Collection<User> users = userRepository.findUsersWithDailyEvents(reportDay);
        System.out.println("USER EVENTS: \n" + users.stream()
                .map(user -> "id = " + user.getId() + ", user = " + user.getUsername() + ", events " + user.getUserEvents().size() + ": " + user.getUserEvents())
                .collect(Collectors.joining("\n")));
        return users;
    }

//    @Override
    public Collection<UserEvent> createUserReport(String username, Date startDate, Date endDate) {
        User user = getUser(username);

        return user.getUserEvents().stream()
                .filter(e -> e.getEventTimestamp().isAfter(startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        && e.getEventTimestamp().isBefore(endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()))
                .collect(Collectors.toList());
    }

    private User getUser(String username) {
        return Objects.requireNonNull(userRepository.findByUsername(username), () -> {
            throw new EntityNotFoundException("Unable to find out the user '" + username + "'");
        });
    }
}
