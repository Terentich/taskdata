package com.taskdata.timetracker.service;

import com.taskdata.timetracker.domain.User;
import com.taskdata.timetracker.domain.UserEvent;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Collection;
import java.util.Date;

@WebService(targetNamespace = TimeTrackerService.TARGET_NAMESPACE)
public interface TimeTrackerService {
    public static final String TARGET_NAMESPACE = "http://taskdata.com/timetracker/ws";

    @WebMethod(action = "createUser")
    @WebResult(name = "User")
    User createUser(@WebParam(name = "Username") String username);

    @WebMethod(action = "deleteUser")
    @WebResult(name = "User")
    User deleteUser(@WebParam(name = "Username") String username);

    @WebMethod(action = "loginUser")
    @WebResult(name = "User")
    User loginUser(@WebParam(name = "Username") String username,
                   @WebParam(name = "EventTimestamp") Date eventTimestamp);

    @WebMethod(action = "logoutUser")
    @WebResult(name = "User")
    User logoutUser(@WebParam(name = "Username") String username,
                    @WebParam(name = "EventTimestamp") Date eventTimestamp);

    @WebMethod(action = "createDailyReport")
    Collection<User> createDailyReport(@WebParam(name = "Date") Date date);

    @WebMethod(action = "createUserReport")
    Collection<UserEvent> createUserReport(@WebParam(name = "Username") String username,
                                           @WebParam(name = "StartDate") Date startDate,
                                           @WebParam(name = "EndDate") Date endDate);
}