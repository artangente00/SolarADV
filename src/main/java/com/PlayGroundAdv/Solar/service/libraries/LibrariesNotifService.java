package com.PlayGroundAdv.Solar.service.libraries;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;

@Service
public class LibrariesNotifService {

	final NotificationEntityService notifService;
	final AuthenticationRepository userRepo;
	
	public LibrariesNotifService(NotificationEntityService notifService, AuthenticationRepository userRepo) {
		super();
		this.notifService = notifService;
		this.userRepo = userRepo;
	}

	public String sendCorrectionRequest(Long updatedBy, String action, String type, String make, String model) {
		try {
			AuthentificationEntity user = userRepo.findById(updatedBy).orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + updatedBy));
			return notifService.addNewNotif(updatedBy, 0L, action, "Libraries",
					"Request Correction - " + model,
					"The user"+user.getFirstName() + " " + user.getLastName()+" requested correction for the "+ type +" "+ make + " "
							+ model + ".",
					true);
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}


	public String editComponent(Long updatedBy, String action, String type, String make, String model) {
		try {
			AuthentificationEntity user = userRepo.findById(updatedBy).orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + updatedBy));
			return notifService.addNewNotif(updatedBy, 0L, action, "Libraries",
					type+" Update - " + model,
					"The "+type+" " + model + " "
							+ model + " was updated by " + user.getFirstName() + " "
							+ user.getLastName(),
					true);
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}



}
