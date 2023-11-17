package com.healthtrack.calculator.service.userCredential;

import com.healthtrack.calculator.mapper.UserCredentialMapper;
import com.healthtrack.calculator.pojo.UserCredential;
import com.healthtrack.calculator.service.security.EncodingService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service("userCredentialService")
public class UserCredentialServiceProvider implements UserCredentialService{


    @Resource
    private UserCredentialMapper userCredentialMapper;

    @Resource
    private EncodingService encodingService;

    @Resource
    private CacheManager cacheManager;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = TransactionException.class)
    public UserCredential getUserCredentialById(UUID uuid) {
        return userCredentialMapper.getUserCredentialById(uuid);
    }

    @Override
    @Cacheable(value = "users_credentials", unless = "#result == null")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = TransactionException.class)
    public UserCredential getUserCredentialByUsername(String username) {
        log.info("setting up cache for users_credentials with key " + username);
        return userCredentialMapper.getUserCredentialByUsername(username);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = TransactionException.class)
    public void insertUserCredential(UserCredential userCredential) {
        log.info("setting up cache for users_credentials with key " + userCredential.getUsername());
        String rawPassword = userCredential.getPassword();
        String encode = encodingService.encode(rawPassword);
        userCredential.setPassword(encode);
        userCredentialMapper.insertUserCredential(userCredential);
        Objects.requireNonNull(cacheManager.getCache("users_credentials")).put(userCredential.getUsername(), userCredential);
    }

    @Override
    @CacheEvict(value = "users_credentials", key = "#username")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = TransactionException.class)
    public void deleteUserCredentialByUsername(String username) {
        log.info("evicting cache for users_credentials with key " + username);
        userCredentialMapper.deleteUserCredentialByUsername(username);
    }
}
