package com.healthtrack.calculator.service.login;

import com.healthtrack.calculator.domain.ResponseBody;
import com.healthtrack.calculator.domain.LogInUser;

public interface LogInService {


    /**
     * Attempts to log in with the request body passed in
     * @param user request body in specified json format
     * @return a specified response body containing all information needed
     */
    ResponseBody<LogInUser> login(LogInUser user);
}
