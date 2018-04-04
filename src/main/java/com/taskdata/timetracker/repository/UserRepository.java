package com.taskdata.timetracker.repository;

import com.taskdata.timetracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);

    @Query(value = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userEvents e WHERE (e.eventTimestamp IS NULL OR FUNCTION('date', e.eventTimestamp) = ?1)")
    Collection<User> findUsersWithDailyEvents(LocalDate date);
}

