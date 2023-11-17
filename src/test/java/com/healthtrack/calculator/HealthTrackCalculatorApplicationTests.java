package com.healthtrack.calculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.healthtrack.calculator.exception.SystemException;
import com.healthtrack.calculator.pojo.UserCredential;
import com.healthtrack.calculator.service.TestService;
import com.healthtrack.calculator.service.email.VerificationCodeService;
import com.healthtrack.calculator.service.email.VerificationCodeServiceImpl;
import com.healthtrack.calculator.service.message.MessageService;
import com.healthtrack.calculator.service.userCredential.UserCredentialService;
import com.healthtrack.calculator.utils.JsonUtil;
import com.healthtrack.calculator.utils.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
@Slf4j
class HealthTrackCalculatorApplicationTests {

    @Resource
    private TestService testService;

    @Resource
    private UserCredentialService userCredentialService;

    @Resource
    private MessageService messageService;

    @Resource
    private VerificationCodeService verificationCodeService;


    @Test
    void contextLoads() {
        boolean a = JwtUtil.validateToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKYW1lczc3N0ciLCJleHAiOjE2ODc1NzI3MTR9._zRNj_lME7ZK-5_JiyLA8zbyMYwzt3QXYHJECfYfdkxI1gdlrw7QqpJeUYlR4vTj-UQO6YKsfLxlFGk8772QRA");
        System.out.println(a);

    }


    @Test
    void test1() throws JsonProcessingException {
        List<String> list = new ArrayList<>();
        list.add("cough");
        list.add("congestion");
        list.add("acidity");
//        list.add("vomiting");
        list.add("fatigue");
//        list.add("anxiety");
        Map<String, List<String>> symptoms = new HashMap<>();
        symptoms.put("symptoms", list);
        System.out.println("The response is: " + messageService.sendAndReceive(JsonUtil.objectToJson(symptoms)));
    }


    @Test
    void test2(){
        UserCredential userCredential = new UserCredential();
        userCredential.setId(UUID.randomUUID());
        userCredential.setUsername("James");
        userCredential.setPassword("1234");
        userCredentialService.insertUserCredential(userCredential);
    }

    @Test
    void testEmailVerificationCode() throws SystemException {
        UserCredential james = userCredentialService.getUserCredentialByUsername("James");
        verificationCodeService.sendVerificationCode(james, "873498");
    }


}
