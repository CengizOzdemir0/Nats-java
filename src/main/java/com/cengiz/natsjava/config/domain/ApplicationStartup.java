package com.cengiz.natsjava.config.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    log.error("BASE Project Ayaklandı....");
    log.error("      \\O/#    O/#   \\O/#    O/#  |_O    \\O/#   O/#'\\   /`   \\O/#");
    log.error("       Y_    _|      Y_     |     _#>    Y_   <|    \\ /  .___Y  ");
    log.error("      /  | _|  \\    /  |   / \\  _|  \\   /  |  / \\    Y       |  ");
    log.error("    ./   |_     \\,./   |_./   \\,    |_./   |__| |_   O\\#     |_ ");
  }
}