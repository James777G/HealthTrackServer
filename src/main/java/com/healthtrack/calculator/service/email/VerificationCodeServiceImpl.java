package com.healthtrack.calculator.service.email;

import com.healthtrack.calculator.exception.SystemException;
import com.healthtrack.calculator.pojo.UserCredential;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.security.SecureRandom;
import java.util.Objects;

@Slf4j
@Service("verificationCodeService")
public class VerificationCodeServiceImpl implements VerificationCodeService{

    private static final String VERIFICATION_CACHE = "verification_codes";

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private SpringTemplateEngine templateEngine;

    @Resource
    private CacheManager cacheManager;

    @Override
    public void sendVerificationCode(UserCredential user, String code) throws SystemException {
        try {
            log.info("SENDING VERIFICATION CODE TO " + user.getUsername());
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("noreply_healthtrack@gmail.com");
            helper.setSubject("Email Verification");
            helper.setTo(user.getEmail());
            Context context = new Context();
            context.setVariable("code", code);

            String htmlContent = templateEngine.process("verificationEmail.html", context);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.info("PUT USERNAME " + user.getUsername() + " AND CODE " + code + " INTO CACHE");
            Objects.requireNonNull(cacheManager.getCache(VERIFICATION_CACHE)).put(user.getUsername(), code);
        } catch (MessagingException e) {
            log.warn("CANNOT CREATE HTML EMAIL");
            throw new SystemException("Cannot Create HTML Email");
        }
    }

    @Override
    public boolean verifyCode(String username, String code) {
        Cache cache = cacheManager.getCache(VERIFICATION_CACHE);
        if (cache != null) {
            String cachedCode = cache.get(username, String.class);
            if (code.equals(cachedCode)) {
                cache.evict(username); // Remove the cache entry after successful verification
                log.info("REMOVE USERNAME " + username + " FROM CACHE");
                return true;
            }
        }
        return false;
    }

    @Override
    public String generateCode() {
        SecureRandom random = new SecureRandom();
        int randomNumber = random.nextInt(1_000_000); // The underscore is just for readability
        return String.format("%06d", randomNumber);
    }


}
