package com.PlayGroundAdv.Solar.service.libraries;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.TemperatureLibraryEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.AllPostalCodeModel;
import com.PlayGroundAdv.Solar.repositories.libraries.TemperatureLibraryRepository;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;

@Service
@Transactional
public class TemperatureLibraryService {

	final TemperatureLibraryRepository temperatureRepo;
	final NotificationEntityService notificationService;

	public TemperatureLibraryService(TemperatureLibraryRepository temperatureRepo,
			NotificationEntityService notificationService) {
		super();
		this.temperatureRepo = temperatureRepo;
		this.notificationService = notificationService;
	}

	// A.B Add Temperature
	public TemperatureLibraryEntity addTemperature(TemperatureLibraryEntity newTemp, Long userID, String userName) {
		try {
			TemperatureLibraryEntity temp = new TemperatureLibraryEntity();
			temp.setPostalCode(newTemp.getPostalCode());
			temp.setFourPerCentAverage(newTemp.getFourPerCentAverage());
			temp.setTwoPerCentAverage(newTemp.getTwoPerCentAverage());
			temp.setExtremeMinimum(newTemp.getExtremeMinimum());
			temperatureRepo.save(temp);
			notificationService.addNewNotif(userID, null, "Add New Temperature", "Libraries",
					"New Temperature - " + newTemp.getPostalCode(),
					"The temperature for the postal code " + newTemp.getPostalCode() + " was added by " + userName,
					true);
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// A.B edit Temperature
	public String editTemperature(TemperatureLibraryEntity editTemp, Long userID, String userName) {
		try {
			TemperatureLibraryEntity temp = temperatureRepo.findById(editTemp.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Temp not Found with ID " + editTemp.getId()));
			temp.setPostalCode(editTemp.getPostalCode());
			temp.setFourPerCentAverage(editTemp.getFourPerCentAverage());
			temp.setTwoPerCentAverage(editTemp.getTwoPerCentAverage());
			temp.setExtremeMinimum(editTemp.getExtremeMinimum());
			temperatureRepo.save(temp);
			notificationService.addNewNotif(userID, null, "Update New Temperature", "Libraries",
					"Update Temperature - " + editTemp.getPostalCode(),
					"The temperature for the postal code " + editTemp.getPostalCode() + " was updated by " + userName,
					true);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	// A.B remove Temperature
	public String removeTemperature(TemperatureLibraryEntity editTemp, Long userID, String userName) {
		try {
			TemperatureLibraryEntity temp = temperatureRepo.findById(editTemp.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Temp not Found with ID " + editTemp.getId()));
			temperatureRepo.delete(temp);
			notificationService.addNewNotif(userID, null, "Delete New Temperature", "Libraries",
					"Delete Temperature - " + editTemp.getPostalCode(),
					"The temperature for the postal code " + editTemp.getPostalCode() + " was deleted by " + userName,
					true);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	// A.B Get All Temperature
	public Page<TemperatureLibraryEntity> getAllTemperature(Integer page, Integer size) {
		try {
			Pageable pageable = PageRequest.of(page, size, Sort.by("postalCode"));
			Page<TemperatureLibraryEntity> listTemp = temperatureRepo.findAll(pageable);
			return listTemp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// A.B Get All Temperature By Potal code
	public Page<TemperatureLibraryEntity> searchTemperature(String postalCode, Integer page, Integer size) {
		try {
			Pageable pageable = PageRequest.of(page, size, Sort.by("postalCode"));
			Page<TemperatureLibraryEntity> listTemp = temperatureRepo.findByPostalCode(postalCode, pageable);
			return listTemp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// A.B Get All Temperature By Potal code
	public List<TemperatureLibraryEntity> getAllTemperature() {
		try {
			return temperatureRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// A.B Get All Temperature By Potal code
	public List<AllPostalCodeModel> getAllPostalCodes() {
		try {
			return temperatureRepo.findAllPostalCode();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
