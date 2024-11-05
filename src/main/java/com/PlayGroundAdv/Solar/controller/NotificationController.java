package com.PlayGroundAdv.Solar.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.NotificationRequest;
import com.PlayGroundAdv.Solar.model.NotificationResponseModel;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	final NotificationEntityService notificationEntityService;

	public NotificationController(NotificationEntityService notificationEntityService) {
		super();
		this.notificationEntityService = notificationEntityService;
	}
	
	@PostMapping("/getUnreadActivities")
	public NotificationResponseModel getUnreadActivities(@RequestBody Long idUser) throws Exception {
		return notificationEntityService.getThirtyUnreadNotification(idUser);
	}
	
	@PostMapping("/getAllActivities")
	public NotificationResponseModel getAllActivities(@RequestBody Long idUser) throws Exception {
		return notificationEntityService.getThirtyNotification(idUser);
	}
	
	@PostMapping("/getTenNotifications/{pas}/{type}")
	public List<NotificationRequest> getTenNotifications(@RequestBody Long idUser,@PathVariable Integer pas,@PathVariable String type) throws Exception {
		return notificationEntityService.getTenNotifications(idUser,pas,type);
	}
	
	@PostMapping("/getTenUnreadNotifications/{pas}/{type}")
	public List<NotificationRequest> getTenUnreadNotifications(@RequestBody Long idUser,@PathVariable Integer pas,@PathVariable String type) throws Exception {
		return notificationEntityService.getTenUnreadNotifications(idUser,pas,type);
	}

	@PostMapping("/setNotifRead")
	public String setNotifRead(@RequestBody Long[] ids) throws Exception {
		return notificationEntityService.setNotifRead(ids[0], ids[1]);
	}

	// 05-14-2019 :M.A : CR-2676 Add Read ALL Notification option
	@PostMapping("/makeAllActivitesRead")
	public String makeAllActivitesRead(@RequestBody Long idUser) throws Exception {
		return notificationEntityService.makeAllActivitesRead(idUser);
	}
}
