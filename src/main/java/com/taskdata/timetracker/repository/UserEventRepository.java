package com.taskdata.timetracker.repository;

import com.taskdata.timetracker.domain.User;
import com.taskdata.timetracker.domain.UserEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRepository extends CrudRepository<UserEvent, Long> {
}
