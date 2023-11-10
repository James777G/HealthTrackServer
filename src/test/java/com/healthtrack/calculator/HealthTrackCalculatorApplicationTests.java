package com.healthtrack.calculator;

import com.healthtrack.calculator.exception.SystemException;
import com.healthtrack.calculator.pojo.UserCredential;
import com.healthtrack.calculator.service.TestService;
import com.healthtrack.calculator.service.email.VerificationCodeService;
import com.healthtrack.calculator.service.email.VerificationCodeServiceImpl;
import com.healthtrack.calculator.service.message.MessageService;
import com.healthtrack.calculator.service.userCredential.UserCredentialService;
import com.healthtrack.calculator.utils.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

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
    void testEmailVerificationCode() throws SystemException {
        UserCredential james = userCredentialService.getUserCredentialByUsername("James");
        verificationCodeService.sendVerificationCode(james, "873498");
    }


}
