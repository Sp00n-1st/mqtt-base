package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configuration.ApplicationConfig.MyGateway;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("mqtt")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Test {

  private final MyGateway myGateway;
  private final MqttPahoClientFactory mqttClientFactory;

  @PostMapping
  public void test() {
    try {
      myGateway.sendToMqtt("Test Rey");
      log.info("Message Send To " + Arrays.toString(mqttClientFactory.getConnectionOptions().getServerURIs()));
    } catch (Exception e) {
      log.info("Failed Message Send" + e.getMessage());
      log.error(null, e);
      throw e;
    }
  }
}
