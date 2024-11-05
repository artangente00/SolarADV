package com.PlayGroundAdv.Solar.service.libraries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Engineers;
import com.PlayGroundAdv.Solar.entity.PermitEngineerEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.EngineersModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.SearchEngineersResult;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.repositories.PermitEngineerRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.EngineersRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class EngineersLibraryService {


	final HistoryActivityService historicActivity;
	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValueTypesService;
	final EngineersRepository engineersRepo;
	final AuthenticationRepository userRepo;
	final PermitEngineerRepository permitEngineerEntityRepo;

	public EngineersLibraryService(HistoryActivityService historicActivity,
			NotificationEntityService notificationEntityService, CheckValueTypesService checkValueTypesService,
			EngineersRepository engineersRepo,
			AuthenticationRepository userRepo, PermitEngineerRepository permitEngineerEntityRepo) {
		super();
		this.historicActivity = historicActivity;
		this.notificationEntityService = notificationEntityService;
		this.checkValueTypesService = checkValueTypesService;
		this.engineersRepo = engineersRepo;
		this.userRepo = userRepo;
		this.permitEngineerEntityRepo = permitEngineerEntityRepo;
	}

	/*
	 * Add engineers
	 */
	public String addEngineers(Long idContractor, SearchEngineersResult engineersReq, String ipUser,
			String timeZoneUser, Long idUser) {

		try {

			List<String> engineerss = engineersRepo.findCompanyByCompanyAndContactAndLicenseState(engineersReq.getCompany(), engineersReq.getContact(),  engineersReq.getLicenseState());
		
			if (engineerss.isEmpty()) {
				Engineers engineers = new Engineers();
				engineers.setCompany(engineersReq.getCompany());
				engineers.setContact(engineersReq.getContact());
				engineers.setAddress(engineersReq.getAddress());
				engineers.setCity(engineersReq.getCity());
				engineers.setState(engineersReq.getState());
				engineers.setZipCode(engineersReq.getZipCode());
				engineers.setLicenseState(engineersReq.getLicenseState());
				engineers.setLicense(engineersReq.getLicense());
				engineers.setLicenseType(engineersReq.getLicenseType());
				engineers.setLicenseExpiration(engineersReq.getLicenseExpiration());
				engineers.setPhone(engineersReq.getPhone());
				engineers.setEmail(engineersReq.getEmail());
				Date d = new Date();
				SimpleDateFormat formattedDATE = new SimpleDateFormat("MM/dd/yyyy");
				String updatedDate = formattedDATE.format(d);
				engineers.setUpdated(updatedDate);

				AuthentificationEntity contractor = new AuthentificationEntity();
				try {
					contractor = userRepo.findById(idContractor).orElseThrow(
							() -> new ResourceNotFoundException("Entity not found for this id :" +idContractor));
				} catch (Exception e) {
					e.printStackTrace();
				}
				engineers.setAuthentificationEntity(contractor);
				
				engineers.setHasSuperUserEdit(contractor.getRoleEntity().getId() == 1 || contractor.getRoleEntity().getId() == 3);
					
				engineersRepo.save(engineers);
				historicActivity.recordActivity(idUser, ipUser, timeZoneUser, "libraries;Add component "
						+ engineers.getContact() + " " + engineers.getLicenseState() + ".;Engineers", true, "", "", "");
				return "Done";
			} else
				historicActivity.recordActivity(idUser, ipUser, timeZoneUser,
						"Add Engineers Component Library;Engineers already exists in data sources;Add failed ", false,
						"", "", "");
			return "Engineers already exists in data sources";

		} catch (Exception e) {
			historicActivity.recordActivity(idUser, ipUser, timeZoneUser, "libraries;Add component.;Engineers", false,
					"", "", "");
			e.printStackTrace();
			return "error";
		}
	}


	/*
	 * edit Engineers
	 */
	public String editEngineers(SearchEngineersResult engineers, Long userID) {
		try {

			Engineers testEngineers = engineersRepo.findById(engineers.getId()).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" +engineers.getId()));

			if (testEngineers != null && !checkValueTypesService.Equals(testEngineers.getId(), engineers.getId())) {
					return "exist";
				} else {
					return editEngineersFunction(engineers, userID);
				}

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String editEngineersFunction(SearchEngineersResult engineers, Long userID) {

		try {
			Engineers updatedEngineers =  engineersRepo.findById(engineers.getId()).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" +engineers.getId()));
			// manufacturer or model has change ---> update (manufacturer,model) in all the
			// project using the edited engineers
			List<PermitEngineerEntity> permitEngineerEntitys = permitEngineerEntityRepo.findByEngineeredBy(Long.toString(updatedEngineers.getId()));
			if (permitEngineerEntitys != null) {
				for (PermitEngineerEntity PermitEngineerEntity : permitEngineerEntitys) {
					if (PermitEngineerEntity != null && PermitEngineerEntity.getEngineeredBy() != null) {
						PermitEngineerEntity.setEngineeredBy(Long.toString(engineers.getId()));
					}

				}
			}
			// end

			SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");

			updatedEngineers.setId(engineers.getId());
			updatedEngineers.setCompany(engineers.getCompany());
			updatedEngineers.setContact(engineers.getContact());
			updatedEngineers.setAddress(engineers.getAddress());
			updatedEngineers.setCity(engineers.getCity());
			updatedEngineers.setState(engineers.getState());
			updatedEngineers.setZipCode(engineers.getZipCode());
			updatedEngineers.setLicenseState(engineers.getLicenseState());
			updatedEngineers.setLicense(engineers.getLicense());
			updatedEngineers.setLicenseType(engineers.getLicenseType());
			updatedEngineers.setLicenseExpiration(engineers.getLicenseExpiration());
			updatedEngineers.setPhone(engineers.getPhone());
			updatedEngineers.setEmail(engineers.getEmail());
			updatedEngineers.setUpdated(formatDate.format(new Date()));
			engineersRepo.save(updatedEngineers);
			historicActivity.recordActivity(userID, "", "", "libraries;Edit component " + engineers.getContact() + " "
					+ engineers.getLicenseState() + ".;Engineers", true, "", "", "");

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userID, "", "", "libraries;Edit component.;Engineers", false, "", "", "");
			return "fail";
		}
	}

	public String editEngineersNotification(Long userID, String contact, String licenseState) {
		try {

			AuthentificationEntity userCo = userRepo.findById(userID).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" +userID));

		      notificationEntityService.addNewNotif(userID, 0L, "Engineers Update", "Libraries",
					"Engineers Update - " + contact + " " + licenseState, "The engineers " + contact + " "
							+ licenseState + " was updated by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String addEngineersNotification(Long userID, String contact, String licenseState) {
		try {

			AuthentificationEntity userCo = userRepo.findById(userID).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" +userID));

	       notificationEntityService.addNewNotif(userID, 0L, "New Engineers", "Libraries",
					"New Engineers- " + contact + " " + licenseState, "The engineers " + contact + " " + licenseState
							+ " was added by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}
	// CR 924
	// Change Request Engineers List Management

	public String engineersLibraryActived(Long idEngineers, Long idUserCo) {

		try {

			Engineers engineersLibrary = engineersRepo.findById(idEngineers).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" +idEngineers));
			if (engineersLibrary != null && engineersLibrary.getId() != 0) {
				if (Boolean.TRUE.equals(engineersRepo.existsByCompanyAndContactAndLicenseStateAndIsDeleted(engineersLibrary.getCompany(),engineersLibrary.getContact(), engineersLibrary.getLicenseState(), false))) {
					return "exist";
				} else {
					engineersLibrary.setDeleted(false);
					historicActivity
							.recordActivity(
									idUserCo, "", "", "libraries;Activate component " + engineersLibrary.getContact()
											+ " " + engineersLibrary.getLicenseState() + ".;Engineers",
									true, "", "", "");
					return "true";
				}

			}
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Activate component.;Engineers", false, "", "",
					"");
			return "false";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Activate component.;Engineers", false, "", "",
					"");
			return "false";

		}
	}

	public List<ProjectForLibrariesModel> getAllPermitOfEngineersDeleted(Long idEngineers) {
		try {
			return permitEngineerEntityRepo.getPermitListForDeletedEngineer(idEngineers + "");
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public boolean deleteEngineersLibrary(Long id, Long idUserCo) {

		try {
			if (id != 0) {
				List<ProjectForLibrariesModel> listPermitDeleted = getAllPermitOfEngineersDeleted(id);
				PermitEngineerEntity permitProjectSiteInfo = null;
				Engineers engineersLibraryRes = engineersRepo.findById(id).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" +id));
				if (listPermitDeleted != null) {
					for (ProjectForLibrariesModel p : listPermitDeleted) {

						permitProjectSiteInfo = permitEngineerEntityRepo.findByPermitEntityIdAndEngineeredBy(p.getId(),
								engineersLibraryRes.getId() + "");
						if (permitProjectSiteInfo != null) {
							permitProjectSiteInfo.setEngineeredBy("Removed");
							permitEngineerEntityRepo.save(permitProjectSiteInfo);
						}

					}
				}

				Engineers engineersLibrary = engineersRepo.findById(id).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" +id));

				engineersLibrary.setDeleted(true);
				engineersRepo.save(engineersLibrary);
				historicActivity.recordActivity(idUserCo, "", "", "libraries;Delete component "
						+ engineersLibrary.getContact() + " " + engineersLibrary.getLicenseState() + ".;Engineers",
						true, "", "", "");

				return true;

			}
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Delete component.;Engineers", false, "", "",
					"");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Delete component.;Engineers", false, "", "",
					"");
			return false;

		}
	}

	public String checkEngineersExistent(SearchEngineersResult newmodule) {
		try {

			if (Boolean.TRUE.equals(engineersRepo.existsByCompanyAndContactAndLicenseStateAndIsDeleted(newmodule.getCompany(),
					newmodule.getContact(), newmodule.getLicenseState(), false))) {

				return "exist";

			} else {
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}

	/*
	 * Add New Engineers
	 */
	public String addNewEngineers(SearchEngineersResult engineersReq, Long idUserCo) {

		Engineers engineers = new Engineers();

		try {

			engineers.setCompany(engineersReq.getCompany());
			engineers.setContact(engineersReq.getContact());
			engineers.setAddress(engineersReq.getAddress());
			engineers.setCity(engineersReq.getCity());
			engineers.setState(engineersReq.getState());
			engineers.setZipCode(engineersReq.getZipCode());
			engineers.setLicenseState(engineersReq.getLicenseState());
			engineers.setLicense(engineersReq.getLicense());
			engineers.setLicenseType(engineersReq.getLicenseType());
			engineers.setLicenseExpiration(engineersReq.getLicenseExpiration());
			engineers.setPhone(engineersReq.getPhone());
			engineers.setEmail(engineersReq.getEmail());
			Date d = new Date();
			SimpleDateFormat formattedDATE = new SimpleDateFormat("MM/dd/yyyy");
			String updatedDate = formattedDATE.format(d);
			engineers.setUpdated(updatedDate);

			AuthentificationEntity userCo = userRepo.findById(idUserCo).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" +idUserCo));

			engineers.setHasSuperUserEdit(userCo.getRoleEntity().getId() == 1 || userCo.getRoleEntity().getId() == 3);

			Date addDate = new Date();
			engineers.setAddDate(addDate);

			engineers.setAuthentificationEntity(userCo);
			engineersRepo.save(engineers);
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Add component " + engineers.getContact() + " "
					+ engineers.getLicenseState() + ".;Engineers", true, "", "", "");
			return "Success";

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Add component.;Engineers", false, "", "", "");
			return "Fail";

		}

	}

	public List<EngineersModel> getListOfEnginners() {
		try {
			return engineersRepo.nonDeletedEngineersContactAndState();
		} catch (Exception e) {
			return new ArrayList<>();
		}

	}

	
	public Page<SearchEngineersResult> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("company").and(Sort.by("contact")).and(Sort.by("licenseState")));
			String companyString = checkValueTypesService.isStringNotEmpty(request.getCompany())
					? request.getCompany().trim()
					: "";
			String contactString = checkValueTypesService.isStringNotEmpty(request.getContact())
					? request.getContact().trim()
					: "";
			String licenceStateString = checkValueTypesService.isStringNotEmpty(request.getLicenseState())
					? request.getLicenseState().trim()
					: "";

			String[] company = companyString.split(" ");
			String[] contact = contactString.split(" ");
			String[] licence = licenceStateString.split(" ");

			Boolean deleted = request.getIsDeleted();

			if (company == null && contact == null && licence == null) {
				Page<Engineers> p = engineersRepo.findByIsDeleted(deleted, pageable);
				return convertDto(p, pageable);
			} else {
				Page<Engineers> p = engineersRepo.findAll(filter(company, contact, licence, deleted), pageable);
				return convertDto(p, pageable);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	private Page<SearchEngineersResult> convertDto(Page<Engineers> page, Pageable pageable) {
		try {
			return new PageImpl<>(
					page.stream().map(c -> new SearchEngineersResult(c.getId(),	c.getCompany(), c.getContact(), 
							c.getAddress(), c.getCity(), c.getState(), c.getZipCode(), c.getLicenseState(),
							c.getLicense(), c.getLicenseType(), c.getLicenseExpiration(), c.getPhone(), c.getEmail(),
							c.getUpdated(), c.isDeleted(),
							new UsersEntityResult(c.getAuthentificationEntity().getId(),
									c.getAuthentificationEntity().getFirstName(),
									c.getAuthentificationEntity().getLastName()))).collect(Collectors.toList()),
					pageable, page.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Specification<Engineers> filter(String[] company, String[] contact, String[] licence, Boolean deleted) {

		return new Specification<Engineers>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Engineers> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicatesCompany = new ArrayList<>();
				List<Predicate> predicatesContact = new ArrayList<>();
				List<Predicate> predicatesLicence = new ArrayList<>();
				for (int i = 0; company != null && i < company.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("company")),
							"%" + company[i].toLowerCase() + "%");
					predicatesCompany.add(predicate);
				}
				for (int i = 0; contact != null && i < contact.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("contact")), "%" + contact[i].toLowerCase() + "%");
					predicatesContact.add(predicate);
				}
				for (int i = 0; licence != null && i < licence.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("licenseState")), "%" + licence[i].toLowerCase() + "%");
					predicatesLicence.add(predicate);
				}
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				Predicate endPredicateCompany = company != null
						? cb.or(predicatesCompany.toArray(new Predicate[predicatesCompany.size()]))
						: null;
				Predicate endPredicateContact = contact != null
						? cb.or(predicatesContact.toArray(new Predicate[predicatesContact.size()]))
						: null;
				Predicate endPredicateLicence = licence != null
						? cb.or(predicatesLicence.toArray(new Predicate[predicatesLicence.size()]))
						: null;

				List<Predicate> list = Arrays.asList(predicateDeleted, endPredicateCompany, endPredicateContact, endPredicateLicence);

				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}
}
