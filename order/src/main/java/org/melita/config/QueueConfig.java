package org.melita.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface QueueConfig {

  String ORDER_OUT = "order";

  @Output(ORDER_OUT)
  MessageChannel order();

}
