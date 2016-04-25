package com.anonyblah.xxalimi;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// WebSocket Connection들의 Endpoint
		registry.addEndpoint("/stomp").withSockJS();
	}
	
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// /topic 접두사를 나가는 Websocket 대화를 위해 사용함
		config.enableSimpleBroker("/topic");
		
		// /app 접두사를 다른 이를 위해 사용
		config.setApplicationDestinationPrefixes("/app");
	}

}
