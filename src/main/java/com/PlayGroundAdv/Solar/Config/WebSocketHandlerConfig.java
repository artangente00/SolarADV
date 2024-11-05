package com.PlayGroundAdv.Solar.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketHandlerConfig implements WebSocketHandler{

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus){
	    System.out.println("***************   afterConnectionClosed  ***************");
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session){ 
		System.out.println("***************   afterConnectionEstablished  *****************");
	}
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message){
		System.out.println("***************   handleMessage  *****************");
	}
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception){
		System.out.println("***************   handleTransportError  *****************");
	}
	@Override
	public boolean supportsPartialMessages(){
	    return true;
	}
}
