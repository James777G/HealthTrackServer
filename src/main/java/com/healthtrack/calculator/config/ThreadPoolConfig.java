package com.healthtrack.calculator.config;

import com.healthtrack.calculator.annotation.Warning;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Thread Pool Java Configuration Class
 * <p>
 *     Core Threads: 2
 *     Max Threads: 4
 *     Task Queue Capacity: 500
 *     Name Prefix: thread-pool-
 *     Reject Policy: Caller Runs
 * </p>
 *
 * Callers utilize the following service by annotating @Async("asyncServiceExecutor")
 */
@Configuration
@EnableAsync
@Slf4j
public class ThreadPoolConfig {

    @Bean
    @Warning(Warning.Type.REVIEW_NEEDED)
    public Executor asyncServiceExecutor(){
        log.info("Thread Pool Service Initialization Start");
        ThreadPoolTaskExecutor executor  = new ThreadPoolTaskExecutor();

        // Core Parameters
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("thread-pool-");


        // Caller Runs or Abort
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        log.info("Thread Pool Service Initialization Completed");
        return executor;
    }

}
