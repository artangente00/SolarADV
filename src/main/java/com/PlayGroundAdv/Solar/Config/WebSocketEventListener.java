package com.PlayGroundAdv.Solar.Config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import com.PlayGroundAdv.Solar.entity.PermitCompanyInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.repositories.PermitCompanyInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Component
public class WebSocketEventListener {

	private final SimpMessagingTemplate messagingTemplate;
	final PermitRepository permitRepo;
	final PermitCompanyInfoRepository utilityCompRepo;
	final CheckValueTypesService checkValue;

	public WebSocketEventListener(SimpMessagingTemplate messagingTemplate, PermitRepository permitRepo,
			PermitCompanyInfoRepository utilityCompRepo, CheckValueTypesService checkValue) {
		super();
		this.messagingTemplate = messagingTemplate;
		this.permitRepo = permitRepo;
		this.utilityCompRepo = utilityCompRepo;
		this.checkValue = checkValue;
	}

	@EventListener
	public void handleSessionSubscribeEvent(SessionSubscribeEvent event)
			throws NumberFormatException, ResourceNotFoundException {
		GenericMessage message = (GenericMessage) event.getMessage();
		SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
		String simpDestination = (String) message.getHeaders().get("simpDestination");
		if (simpDestination != null && simpDestination.startsWith("/watch-project/")
				&& checkValue.isNumericNotZero(simpDestination.split("/")[2])) {
			PermitEntity permitEntity = permitRepo.findById(Long.valueOf(simpDestination.split("/")[2]))
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
			if (permitEntity != null) {
				permitEntity.setWebSocketSession(headers.getSessionId());
				permitRepo.save(permitEntity);
			}
		}else if (simpDestination != null && simpDestination.startsWith("/watch-utility/")
				&& checkValue.isNumericNotZero(simpDestination.split("/")[2])){
			PermitCompanyInfoEntity utility = utilityCompRepo.findById(Long.valueOf(simpDestination.split("/")[2]))
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
			if (utility != null) {
				utility.setWebSocketSession(headers.getSessionId());
				utilityCompRepo.save(utility);
			}
		}
	}

	@EventListener
	public void handleSessionDisconnectEvent(SessionDisconnectEvent event) {

		if (checkValue.isStringNotEmpty(event.getSessionId())) {
			PermitEntity permitEntity = permitRepo.findByWebSocketSessionAndOpenedTrue(event.getSessionId());
			if (permitEntity != null) {
				permitEntity.setOpened(false);
				permitEntity.setOpenedBy(null);
				permitEntity.setWebSocketSession(null);
				permitRepo.save(permitEntity);
				this.messagingTemplate.convertAndSend("/enable-project/" + permitEntity.getId(), false);
			}else {
				PermitCompanyInfoEntity utility = utilityCompRepo.findByWebSocketSessionAndOpenedTrue(event.getSessionId());
				if (utility != null) {
					utility.setOpened(false);
					utility.setOpenedBy(null);
					utility.setWebSocketSession(null);
					utilityCompRepo.save(utility);
					this.messagingTemplate.convertAndSend("/enable-utility/" + utility.getId(), false);
				}
				
			}
		}
	}

}
