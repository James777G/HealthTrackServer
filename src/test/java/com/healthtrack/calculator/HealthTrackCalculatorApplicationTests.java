package com.healthtrack.calculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.healthtrack.calculator.domain.UserInfo;
import com.healthtrack.calculator.pojo.UserCredential;
import com.healthtrack.calculator.service.TestService;
import com.healthtrack.calculator.service.message.MessageService;
import com.healthtrack.calculator.service.userCredential.UserCredentialService;
import com.healthtrack.calculator.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.UUID;

import static com.healthtrack.calculator.utils.JsonUtil.objectToJson;

@SpringBootTest
class HealthTrackCalculatorApplicationTests {

    @Resource
    private TestService testService;

    @Resource
    private UserCredentialService userCredentialService;

    @Resource
    private MessageService messageService;

    @Test
    void contextLoads() {
        boolean a = JwtUtil.validateToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKYW1lczc3N0ciLCJleHAiOjE2ODc1NzI3MTR9._zRNj_lME7ZK-5_JiyLA8zbyMYwzt3QXYHJECfYfdkxI1gdlrw7QqpJeUYlR4vTj-UQO6YKsfLxlFGk8772QRA");
        System.out.println(a);


    }


    @Test
    void test1(){
        System.out.println(messageService.sendAndReceive("peter wang").toString());
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
    void test3(){

        UserInfo info = new UserInfo();
        info.setAge(18);
        info.setUserName("pete");
        HashSet<String> set = new HashSet<>();
        set.add("cough");
        set.add("fever");
        info.setSymptoms(set);
        try {
            String s = objectToJson(info);
            Object r = messageService.sendAndReceive(s);
            System.out.println(r.toString());
        } catch (JsonProcessingException e) {
            System.out.println("json error");
            e.printStackTrace();
        }

    }

}
