package com.taskdata.timetracker.repository;

import com.taskdata.timetracker.domain.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
}
