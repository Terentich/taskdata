package com.taskdata.timetracker.service;

import com.taskdata.timetracker.domain.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;
import java.util.Collection;

@WebService(targetNamespace = TimeTrackerService.TARGET_NAMESPACE)
public interface TimeTrackerService {
    public static final String TARGET_NAMESPACE = "http://taskdata.com/timetracker/ws";

    @WebMethod(action = "createUser")
    @ResponseWrapper(className = "com.taskdata.timetracker.service.CreateUserResponse")
    @WebResult(name = "CreateUserResponse")
    User createUser(@WebParam(name = "CreateUserRequest") CreateUserRequest createUserRequest);

    @WebMethod(action = "deleteUser")
    @ResponseWrapper(className = "com.taskdata.timetracker.service.DeleteUserResponse")
    User deleteUser(@WebParam(name = "DeleteUserRequest") DeleteUserRequest deleteUserRequest);

    @WebMethod(action = "loginUser")
    @ResponseWrapper(className = "com.taskdata.timetracker.service.LoginUserResponse")
    User loginUser(@WebParam(name = "LoginUserRequest") LoginUserRequest loginUserRequest);

    @WebMethod(action = "logoutUser")
    @ResponseWrapper(className = "com.taskdata.timetracker.service.LogoutUserResponse")
    User logoutUser(@WebParam(name = "LogoutUserRequest") LogoutUserRequest logoutUserRequest);

    @WebMethod(action = "createDailyReport")
    @ResponseWrapper(className = "com.taskdata.timetracker.service.CreateDailyReportResponse")
    Collection<User> createDailyReport(@WebParam(name = "CreateDailyRequest") CreateDailyReportRequest createDailyReportRequest);

//    @WebMethod(action = "createUserReport")
//    @WebResult(name = "UserEvents")
//    Collection<UserEvent> createUserReport(@WebParam(name = "Username") String username,
//                                           @WebParam(name = "StartDate") Date startDate,
//                                           @WebParam(name = "EndDate") Date endDate);
}