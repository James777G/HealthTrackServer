package com.healthtrack.calculator.service.login;

import com.healthtrack.calculator.domain.ResponseBody;
import com.healthtrack.calculator.domain.SignUpRequest;
import com.healthtrack.calculator.exception.SystemException;

public interface SignUpService {

    ResponseBody<SignUpRequest> signUp(SignUpRequest request) throws SystemException;

    ResponseBody<SignUpRequest> verify(SignUpRequest request);
}
