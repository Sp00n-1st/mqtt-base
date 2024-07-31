package com.example.demo.configuration;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;

import com.example.demo.configuration.properties.MqttProp;

@Configuration
public class ApplicationConfig {

  @Bean
  public MqttPahoClientFactory mqttClientFactory(MqttProp prop) {
    DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
    MqttConnectOptions options = new MqttConnectOptions();
    options.setServerURIs(new String[] { prop.getBrokerAddress() });
    options.setUserName(prop.getUsername());
    options.setPassword(prop.getPassword().toCharArray());
    factory.setConnectionOptions(options);
    return factory;
  }

  @Bean
  @ServiceActivator(inputChannel = "mqttOutboundChannel")
  public MqttPahoMessageHandler mqttOutbound(MqttProp prop) {
    MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(prop.getClientId(), mqttClientFactory(prop));
    messageHandler.setAsync(true);
    messageHandler.setDefaultTopic("test/rey79");
    return messageHandler;
  }

  @Bean
  public MessageChannel mqttOutboundChannel() {
    return new DirectChannel();
  }

  @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
  public interface MyGateway {

    void sendToMqtt(String data);

  }
}
