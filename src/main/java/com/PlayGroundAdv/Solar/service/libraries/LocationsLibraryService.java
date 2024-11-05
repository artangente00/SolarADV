package com.PlayGroundAdv.Solar.service.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ChecklistLocationsEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.libraries.CheckLocationsModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.LocationLibraryRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class LocationsLibraryService {
	
	final HistoryActivityService historyActivityService;
	final CheckValueTypesService checkValueTypesService;
	final AuthenticationRepository userRepo;
	final LocationLibraryRepository locationRepo;
	final PathRepository pathRepo;
	
	
	public LocationsLibraryService(HistoryActivityService historyActivityService,
			CheckValueTypesService checkValueTypesService,
			AuthenticationRepository userRepo,
			LocationLibraryRepository locationRepo,
			PathRepository pathRepo) {
		super();
		this.historyActivityService = historyActivityService;
		this.checkValueTypesService = checkValueTypesService;
		this.userRepo = userRepo;
		this.locationRepo = locationRepo;
		this.pathRepo = pathRepo;
	}

	public String getfilesPath() {
		
		try {
			return pathRepo.findFilePath();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "";
		}
	}

	/*
	 * Edit Location
	 */
	public String editLocation(CheckLocationsModel location, String ipUser, String timeZoneUser, Long idUserCo,String numTab,String sessionId, String openDate) {
		try { 

			ChecklistLocationsEntity newLocation = locationRepo.findById(location.getId()).orElse(null);
			newLocation.setLocationId(location.getLocationId());
			newLocation.setLocationName(location.getLocationName());
			newLocation.setInstructionGuide(location.getInstructionGuide());
			newLocation.setAttachement(location.getAttachement());
			newLocation.setRoofMounted(location.getRoofMounted());
			newLocation.setGroundMounted(location.getGroundMounted());
			newLocation.setPoleMounted(location.getPoleMounted());
			newLocation.setPatioCover(location.getPatioCover());
			newLocation.setOtherMounted(location.getOtherMounted());
			newLocation.setOtherMountedValue(location.getOtherMountedValue());
			newLocation.setAhj(location.getAhj());
			newLocation.setServiceVoltage(location.getServiceVoltage());
			newLocation.setRoofingMaterial(location.getRoofingMaterial());
			newLocation.setInverterTechnology(location.getInverterTechnology());
			newLocation.setMoreThanOneInverter(location.getMoreThanOneInverter());
			newLocation.setPointOfConnection(location.getPointOfConnection());
			newLocation.setPairedWithBatteryStorage(location.getPairedWithBatteryStorage());
			newLocation.setAhjValue(location.getAhjValue());
			newLocation.setServiceVoltageValue(location.getServiceVoltageValue());
			newLocation.setRoofingMaterialValue(location.getRoofingMaterialValue());
			newLocation.setInverterTechnologyValue(location.getInverterTechnologyValue());
			newLocation.setMoreThanOneInverterValue(location.getMoreThanOneInverterValue());
			newLocation.setPointOfConnectionValue(location.getPointOfConnectionValue());
			newLocation.setPairedWithBatteryStorageValue(location.getPairedWithBatteryStorageValue());
			newLocation.setIsShown(location.getIsShown());

			newLocation.setIsDeleted(false);

			locationRepo.save(newLocation);
			
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser, "Edit Checklist Location "
					+ location.getLocationId() + " ;Module Id " + location.getId() + "Add Success ", true,numTab,sessionId,openDate);
			return "Done";

		} catch (Exception e) {
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser, "", false,numTab,sessionId,openDate);
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"Edit Checklist Location;error technical problem;Add failed ", false,numTab,sessionId,openDate);
			return "error";
		}

	}

	/*
	 * Edit Location
	 */
	public String editLocationShowing(Long locationID, Boolean isShown, String ipUser, String timeZoneUser,
			Long idUserCo,String numTab,String sessionId, String openDate) {
		try {
			ChecklistLocationsEntity editedLocation = locationRepo.findById(locationID).orElseThrow(
					() -> new ResourceNotFoundException("Location not found for this id :" +locationID));
			editedLocation.setIsShown(isShown);
			String showing = "";
			if (checkValueTypesService.Equals(isShown,true)) {
				showing = "to be shown in the evaluation checklist";
			} else
				showing = "to be not shown in the evaluation checklist";
			locationRepo.save(editedLocation);
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"Edit Checklist Location " + editedLocation.getLocationId() + " " + showing + " ;Module Id "
							+ editedLocation.getId() + "Add Success ",
					true,numTab,sessionId,openDate);

			return "Done";

		} catch (Exception e) {
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser, "", false,numTab,sessionId,openDate);
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"Edit Checklist Location;error technical problem;Add failed ", false,numTab,sessionId,openDate);
			return "error";
		}

	}

	/*
	 * Add New Location
	 */

	public ChecklistLocationsEntity addNewLocation(ChecklistLocationsEntity location, String tempFile, String ipUser,
			String timeZoneUser, Long idUserCo,String numTab,String sessionId, String openDate) {
		ChecklistLocationsEntity exceptionRsl = new ChecklistLocationsEntity();
		try {
			AuthentificationEntity owner = userRepo.findById(idUserCo).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +idUserCo));
			ChecklistLocationsEntity newLocation = new ChecklistLocationsEntity();
			newLocation.setLocationId(location.getLocationId());
			newLocation.setLocationName(location.getLocationName());
			newLocation.setInstructionGuide(location.getInstructionGuide());
			newLocation.setAttachement(location.getAttachement());
			newLocation.setRoofMounted(location.getRoofMounted());
			newLocation.setGroundMounted(location.getGroundMounted());
			newLocation.setPoleMounted(location.getPoleMounted());
			newLocation.setPatioCover(location.getPatioCover());
			newLocation.setOtherMounted(location.getOtherMounted());
			newLocation.setOtherMountedValue(location.getOtherMountedValue());
			newLocation.setAhj(location.getAhj());
			newLocation.setServiceVoltage(location.getServiceVoltage());
			newLocation.setRoofingMaterial(location.getRoofingMaterial());
			newLocation.setInverterTechnology(location.getInverterTechnology());
			newLocation.setMoreThanOneInverter(location.getMoreThanOneInverter());
			newLocation.setPointOfConnection(location.getPointOfConnection());
			newLocation.setPairedWithBatteryStorage(location.getPairedWithBatteryStorage());
			newLocation.setAhjValue(location.getAhjValue());
			newLocation.setServiceVoltageValue(location.getServiceVoltageValue());
			newLocation.setRoofingMaterialValue(location.getRoofingMaterialValue());
			newLocation.setInverterTechnologyValue(location.getInverterTechnologyValue());
			newLocation.setMoreThanOneInverterValue(location.getMoreThanOneInverterValue());
			newLocation.setPointOfConnectionValue(location.getPointOfConnectionValue());
			newLocation.setPairedWithBatteryStorageValue(location.getPairedWithBatteryStorageValue());
			newLocation.setIsShown(location.getIsShown());
			newLocation.setOwner(owner);

			newLocation.setIsDeleted(false);
			newLocation.setAttachement(tempFile);

			locationRepo.save(newLocation);

			// test how to copy a file from a folder to an other
			String path = pathRepo.findFilePath();
			Path path2 = Paths.get(path + "/Location Attachement");
			Path pathLo = Paths.get(path + "/Location Attachement/" + location.getId());

			// test if folder ID location exist else create
			if (Files.notExists(path2, LinkOption.NOFOLLOW_LINKS)) {
				new File(path + "/Location Attachement").mkdir();
			}

			if (Files.notExists(pathLo, LinkOption.NOFOLLOW_LINKS)) {
				new File(path + "/Location Attachement/" + location.getId()).mkdir();
			}

			// boucle sur les fichier temporaire
			// faire une copie dans le dossier attachement/idLocation
			if (checkValueTypesService.NotEquals(tempFile,"")) {
				if (tempFile.contains("::")) {
					String[] filesTemp = tempFile.split("::");

					for (int i = 0; i < filesTemp.length; i++) {

						if (checkValueTypesService.NotEquals(filesTemp[i],"")) {

							File from = new File(path + "/locationFile/" + filesTemp[i]);
							File to = new File(path + "/Location Attachement/" + location.getId() + "/" + filesTemp[i]);

							InputStream is = null;
							OutputStream os = null;
							try {
								is = new FileInputStream(from);
								os = new FileOutputStream(to);
								// buffer size 1K
								byte[] buf = new byte[1024];
								int bytesRead;
								while ((bytesRead = is.read(buf)) > 0) {
									os.write(buf, 0, bytesRead);
								}
								is.close();
								os.close();
							} catch (Exception e) {
								e.printStackTrace();
								is.close();
								os.close();
							}
							// delete temp file
							try {
								Files.deleteIfExists(from.toPath()); // surround it in try catch block
							} catch (Exception e) {
								e.printStackTrace();
							}

						}

					}
				}
			}

			// END test how to copy a file from a folder to an other

			// delete old file

			// add File attachement to the data tabe nader

			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"Add New Location " + location.getLocationId() + " to the Checklist Library;Module Id "
							+ location.getId() + "Add Success ",
					true,numTab,sessionId,openDate);
			return newLocation;

		} catch (Exception e) {
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser, "", false,numTab,sessionId,openDate);
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"Add New Location to the Checklist Library;error technical problem;Add failed ", false,numTab,sessionId,openDate);
			return exceptionRsl;
		}

	}

	/*
	 * Delete Location
	 */
	public String deleteLocation(String locationID, String ipUser, String timeZoneUser, Long idUserCo,String numTab,String sessionId, String openDate) {
		try {
			ChecklistLocationsEntity editedLocation = locationRepo.findById(Long.valueOf(locationID)).orElseThrow(
					() -> new ResourceNotFoundException("Location not found for this id :" +Long.valueOf(locationID)));
			editedLocation.setIsDeleted(true);
			editedLocation.setIsShown(false);

			locationRepo.save(editedLocation);

			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser, "Delete Checklist Location "
					+ editedLocation.getLocationId() + " ;Module Id " + editedLocation.getId() + "Add Success ", true,numTab,sessionId,openDate);
			return "Done";

		} catch (Exception e) {
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser, "", false,numTab,sessionId,openDate);
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"Delete Checklist Location;error technical problem;Add failed ", false,numTab,sessionId,openDate);
			return "error";
		}

	}

	/*
	 * Activate Location
	 */
	public String activateLocation(String locationID, String ipUser, String timeZoneUser, Long idUserCo,String numTab,String sessionId, String openDate) {
		try {
			ChecklistLocationsEntity editedLocation = locationRepo.findById(Long.valueOf(locationID)).orElseThrow(
					() -> new ResourceNotFoundException("Location not found for this id :" +Long.valueOf(locationID)));
			editedLocation.setIsDeleted(false);
			locationRepo.save(editedLocation);
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser, "Activate Checklist Location "
					+ editedLocation.getLocationId() + " ;Module Id " + editedLocation.getId() + "Add Success ", true,numTab,sessionId,openDate);
			return "Done";

		} catch (Exception e) {
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser, "", false,numTab,sessionId,openDate);
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"Activate Checklist Location;error technical problem;Add failed ", false,numTab,sessionId,openDate);
			return "error";
		}
	}

	/*
	 * Search Location
	 */
	
	public Page<CheckLocationsModel> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("locationName"));
			String locationId = checkValueTypesService.isStringNotEmpty(request.getLocationId())
					? request.getLocationId().toUpperCase()
					: null;
			String locationName = checkValueTypesService.isStringNotEmpty(request.getLocationName())
					? request.getLocationName().toUpperCase()
					: null;
			String projectType = checkValueTypesService.isStringNotEmpty(request.getProjectType())
					? request.getProjectType()
					: null;
			if (locationId == null && locationName == null && projectType == null && !Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<ChecklistLocationsEntity> p = locationRepo.findByIsDeleted(request.getIsDeleted(), pageable);
				return convertDto(p, pageable);
			} else {
				Page<ChecklistLocationsEntity> p = locationRepo.findAll(filter(locationId, locationName, projectType, request.getIsFavorite(), request.getIsDeleted()), pageable);
				return convertDto(p, pageable);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Page<CheckLocationsModel> convertDto(Page<ChecklistLocationsEntity> page, Pageable pageable) {
		try {
			return new PageImpl<>(
					page.stream().map(c -> new CheckLocationsModel(c.getId(), c.getOwner().getFirstName() + " " +c.getOwner().getLastName(),
							c.getLocationId(), c.getLocationName(), c.getInstructionGuide(), c.getAttachement(), c.getRoofMounted(),
							c.getGroundMounted(), c.getPoleMounted(), c.getPatioCover(), c.getOtherMounted(), c.getOtherMountedValue(),
							c.getAhj(), c.getServiceVoltage(), c.getRoofingMaterial(), c.getInverterTechnology(), c.getMoreThanOneInverter(),
							c.getPointOfConnection(), c.getPairedWithBatteryStorage(), c.getAhjValue(), c.getServiceVoltageValue(),
							c.getRoofingMaterialValue(), c.getInverterTechnologyValue(), c.getMoreThanOneInverterValue(), c.getPointOfConnectionValue(),
							c.getPairedWithBatteryStorageValue(), c.getIsShown(), c.getIsDeleted())).collect(Collectors.toList()),
					pageable, page.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Specification<ChecklistLocationsEntity> filter(String locationId, String locationName, String projectType, Boolean favorite, Boolean deleted) {

		return new Specification<ChecklistLocationsEntity>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ChecklistLocationsEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateLocationId = locationId != null ? cb.like(cb.upper(root.get("locationId")), "%" +locationId+ "%") : null;
				Predicate predicateLocationName = locationName != null ? cb.like(cb.upper(root.get("locationName")), "%" +locationName+ "%") : null;
				Predicate predicateFavorite = Boolean.TRUE.equals(favorite) ? cb.equal(root.get("isShown"), favorite) : null;
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				List<Predicate> predicateProjectType = new ArrayList<>();
				if( projectType != null ) {
				switch (projectType) {
				case "All":
					predicateProjectType.add(cb.equal(root.get("roofMounted"), true));
					predicateProjectType.add(cb.equal(root.get("groundMounted"), true));
					predicateProjectType.add(cb.equal(root.get("poleMounted"), true));
					predicateProjectType.add(cb.equal(root.get("patioCover"), true));
					break;
				case "Roof Mounted":
					predicateProjectType.add(cb.equal(root.<Boolean>get("roofMounted"), true));
					break;

				case "Ground Mounted":
					predicateProjectType.add(cb.equal(root.<Boolean>get("groundMounted"), true));
					break;

				case "Pole Mounted":
					predicateProjectType.add(cb.equal(root.<Boolean>get("poleMounted"), true));
					break;

				case "Carport Patio Cover":
					predicateProjectType.add(cb.equal(root.<Boolean>get("patioCover"), true));
					break;

				case "Other":
					predicateProjectType.add(cb.equal(root.<Boolean>get("otherMounted"), true));
					break;

				default:
					break;
				}
				}
				Predicate endPredicateProjectType = projectType != null
						? cb.or(predicateProjectType.toArray(new Predicate[predicateProjectType.size()]))
						: null;
				List<Predicate> list = Arrays.asList(predicateLocationId, predicateFavorite, predicateLocationName, predicateDeleted, endPredicateProjectType);

				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}
	
	public String pictureupload(MultipartFile file, RedirectAttributes redirectAttributes) {
		String name = file.getOriginalFilename();
		String[] parts = name.split("_");
		String part1 = parts[0];
		String part2 = parts[1];

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}

		if (!new File(getfilesPath() + part1 + "//").exists()) {
			new File(getfilesPath() + part1 + "//").mkdir();
		}

		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(getfilesPath() + part1 + "//" + part2);
			Files.write(path, bytes);
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/uploadStatus";
	}

}
