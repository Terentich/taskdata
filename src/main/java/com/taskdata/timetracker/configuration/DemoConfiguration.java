package com.taskdata.timetracker.configuration;

import com.taskdata.timetracker.TimeTracker;
import com.taskdata.timetracker.domain.EventType;
import com.taskdata.timetracker.domain.User;
import com.taskdata.timetracker.domain.UserEvent;
import com.taskdata.timetracker.repository.UserEventRepository;
import com.taskdata.timetracker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;

@Profile("demo")
@Configuration
public class DemoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(TimeTracker.class);

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, UserEventRepository userEventRepository) {
        return (args) -> {
            User user1 = new User("Joe Black");
            UserEvent userEvent1 = new UserEvent(EventType.LOGIN, LocalDateTime.now().minusHours(4));
            UserEvent userEvent2 = new UserEvent(EventType.LOGOUT, LocalDateTime.now());
            UserEvent userEvent11 = new UserEvent(EventType.LOGIN, LocalDateTime.now().minusDays(3).minusHours(2));
            UserEvent userEvent12 = new UserEvent(EventType.LOGOUT, LocalDateTime.now().minusDays(3));
            user1.setUserEvents(Arrays.asList(userEvent1, userEvent2, userEvent11, userEvent12));
            userRepository.save(user1);

            User user2 = new User("Иван Васильевич");
            UserEvent userEvent3 = new UserEvent(EventType.LOGIN, LocalDateTime.now().minusDays(10).minusHours(8));
            UserEvent userEvent4 = new UserEvent(EventType.LOGOUT, LocalDateTime.now().minusDays(10).minusHours(6));
            user2.setUserEvents(Arrays.asList(userEvent3, userEvent4));
            userRepository.save(user2);

            User user3 = new User("Иван Иваныч");
            UserEvent userEvent5 = new UserEvent(EventType.LOGIN, LocalDateTime.now().minusDays(3).plusHours(2));
            UserEvent userEvent6 = new UserEvent(EventType.LOGOUT, LocalDateTime.now().minusDays(1).plusHours(3));
            UserEvent userEvent55 = new UserEvent(EventType.LOGIN, LocalDateTime.now().minusDays(12).plusHours(2));
            UserEvent userEvent66 = new UserEvent(EventType.LOGOUT, LocalDateTime.now().minusDays(11));
            UserEvent userEvent67 = new UserEvent(EventType.LOGIN, LocalDateTime.now().minusHours(7));
            user3.setUserEvents(Arrays.asList(userEvent5, userEvent6, userEvent55, userEvent66, userEvent67));
            userRepository.save(user3);

            User user4 = new User("Michael Jackson");
            UserEvent userEvent7 = new UserEvent(EventType.LOGIN, LocalDateTime.now().minusMonths(1));
            user4.setUserEvents(Arrays.asList(userEvent7));
            userRepository.save(user4);

            userRepository.save(new User("Mickey Mouse"));

            System.out.println("============== DEMO ==============");
            System.out.println("Registered events:");
            userEventRepository.findAll().stream()
                    .sorted(Comparator.comparing(UserEvent::getEventTimestamp))
                    .forEach(System.out::println);
        };
    }
}
