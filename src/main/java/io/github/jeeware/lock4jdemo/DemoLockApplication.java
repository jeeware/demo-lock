package io.github.jeeware.lock4jdemo;

import io.github.jeeware.cloud.lock4j.spring.annotation.DistributedLock;
import io.github.jeeware.cloud.lock4j.spring.annotation.EnableDistributedLock;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDistributedLock
@EnableScheduling
@RequiredArgsConstructor
public class DemoLockApplication {

    static final Logger log = LoggerFactory.getLogger(DemoLockApplication.class);

    final CounterRepository counterRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoLockApplication.class, args);
    }


    @Scheduled(fixedRate = 2000)
    @DistributedLock(mode = DistributedLock.Mode.TRY_LOCK)
    public void chronometer() throws InterruptedException {
        final Counter counter = counterRepository.findById("test").orElseGet(() -> new Counter("test", 0));
        counterRepository.save(counter.increment());
        log.info(">>>> Increment counter={}", counter);
        TimeUnit.SECONDS.sleep(1);
    }

}
