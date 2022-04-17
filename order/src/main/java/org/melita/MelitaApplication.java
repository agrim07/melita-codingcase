package org.melita;

import org.melita.config.QueueConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(QueueConfig.class)
public class MelitaApplication {

  public static final Logger log = LoggerFactory.getLogger(MelitaApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(MelitaApplication.class, args);
    log.info("Melita order placing Application has started...");
  }
}
