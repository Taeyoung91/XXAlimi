package com.anonyblah.xxalimi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry
			.addEndpoint("/WebSocket")
			.withSockJS()
			.setSessionCookieNeeded(true)
			.setInterceptors(new HttpSessionHandshakeInterceptor());

		// WebSocket Connection들의 Endpoint
	}
		
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// /topic 접두사를 나가는 Websocket 대화를 위해 사용함
		registry.enableSimpleBroker("/topic", "/message");
		
		// 특정 사용자를 위한 접두사, @SendToUser와 관련
		registry.setUserDestinationPrefix("/user");
		
		// /app 접두사를 다른 이를 위해 사용
		registry.setApplicationDestinationPrefixes("/app");
	}

	
//	protected void configureInbound(MessageSecurityMetadataSourceRegistry messages){
//		 messages
//		    .nullDestMatcher().authenticated()
//		    .simpSubscribeDestMatchers("/user/queue/errors").permitAll()
//		    .simpDestMatchers("/app/**").hasRole("ADMIN")
//		    .simpSubscribeDestMatchers("/user/**").hasRole("ADMIN")
//		    .simpTypeMatchers(SimpMessageType.MESSAGE, SimpMessageType.SUBSCRIBE).denyAll()
//		    .anyMessage().denyAll();
//	}

}
