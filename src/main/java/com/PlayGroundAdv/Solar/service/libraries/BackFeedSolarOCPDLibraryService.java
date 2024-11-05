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
import com.PlayGroundAdv.Solar.entity.BackFeedSolarOCPD;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.BackFeedSolarOCPDEntityModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.SearchBackFeedSolarOCPDResult;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BackFeedSolarOCPDRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class BackFeedSolarOCPDLibraryService {


	final HistoryActivityService historicActivity;
	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValueTypesService;
	final BackFeedSolarOCPDRepository backFeedSolarOCPDRepo;
	final AuthenticationRepository authentificationRepo;
	final PermitArraysRepository permitArraysEntityRepo;
	final PermitRepository permitEntityRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo;

	public BackFeedSolarOCPDLibraryService(HistoryActivityService historicActivity,
			NotificationEntityService notificationEntityService, CheckValueTypesService checkValueTypesService,
			BackFeedSolarOCPDRepository backFeedSolarOCPDRepo,
			AuthenticationRepository authentificationRepo, PermitArraysRepository permitArraysEntityRepo,
			PermitRepository permitEntityRepo, PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo) {
		super();
		this.historicActivity = historicActivity;
		this.notificationEntityService = notificationEntityService;
		this.checkValueTypesService = checkValueTypesService;
		this.backFeedSolarOCPDRepo = backFeedSolarOCPDRepo;
		this.authentificationRepo = authentificationRepo;
		this.permitArraysEntityRepo = permitArraysEntityRepo;
		this.permitEntityRepo = permitEntityRepo;
		this.permitProjectSiteInfoEntityRepo = permitProjectSiteInfoEntityRepo;
	}
	
	public Page<SearchBackFeedSolarOCPDResult> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("backFeed"));
			Integer backFeed = request.getBackFeed();

			Boolean deleted = request.getIsDeleted();

			if (backFeed == null) {
				Page<BackFeedSolarOCPD> p = backFeedSolarOCPDRepo.findByIsDeleted(deleted, pageable);
				return convertDto(p, pageable);
			} else {
				Page<BackFeedSolarOCPD> p = backFeedSolarOCPDRepo.findByBackFeedAndIsDeleted(backFeed, deleted, pageable);
				return convertDto(p, pageable);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Page<SearchBackFeedSolarOCPDResult> convertDto(Page<BackFeedSolarOCPD> page, Pageable pageable) {
		try {
			return new PageImpl<>(
					page.stream().map(c -> new SearchBackFeedSolarOCPDResult(c.getId(),	c.getBackFeed(), c.getUpdated(),
							c.isDeleted(),
							new UsersEntityResult(c.getAuthentificationEntity().getId(),
									c.getAuthentificationEntity().getFirstName(),
									c.getAuthentificationEntity().getLastName()),
							c.getAuthentificationEntity().getFirstName() + " " + c.getAuthentificationEntity().getLastName())).collect(Collectors.toList()),
					pageable, page.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * edit BackFeedSolarOCPD
	 */
	public String editBackFeedSolarOCPD(SearchBackFeedSolarOCPDResult backFeedSolarOCPD, Long userID) {
		try {
			if (backFeedSolarOCPDRepo.countByBackFeedAndIsDeleted(backFeedSolarOCPD.getBackFeed(), false) != 0) {
				BackFeedSolarOCPD testBackFeedSolarOCPD = backFeedSolarOCPDRepo
						.findByBackFeedAndIsDeleted(backFeedSolarOCPD.getBackFeed(), false);
				if (testBackFeedSolarOCPD != null && !checkValueTypesService.Equals(testBackFeedSolarOCPD.getId(),backFeedSolarOCPD.getId())) {
					return "exist";
				} else {
					return editBackFeedSolarOCPDFunction(backFeedSolarOCPD, userID);

				}
			} else {
				return editBackFeedSolarOCPDFunction(backFeedSolarOCPD, userID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String editBackFeedSolarOCPDFunction(SearchBackFeedSolarOCPDResult backFeedSolarOCPD, Long userID) {

		try {
			BackFeedSolarOCPD updatedBackFeedSolarOCPD = backFeedSolarOCPDRepo.findById(backFeedSolarOCPD.getId()).orElseThrow(
					() -> new ResourceNotFoundException("OCPD not found for this id :" +backFeedSolarOCPD.getId()));
			// manufacturer or model has change ---> update (manufacturer,model) in all the
			// project using the edited backFeedSolarOCPD
			List<PermitProjectSiteInfoEntity> permitProjectSiteInfoEntitys = permitProjectSiteInfoEntityRepo
					.findListOfProjectsWithOCPD(Integer.toString(updatedBackFeedSolarOCPD.getBackFeed()));
			if (permitProjectSiteInfoEntitys != null && !permitProjectSiteInfoEntitys.isEmpty()) {

				for (PermitProjectSiteInfoEntity PermitProjectSiteInfoEntity : permitProjectSiteInfoEntitys) {
					if (checkValueTypesService.Equals(PermitProjectSiteInfoEntity.getSolarInterconnection(),
							Integer.toString(updatedBackFeedSolarOCPD.getBackFeed()))) {
						PermitProjectSiteInfoEntity
								.setSolarInterconnection(Integer.toString(backFeedSolarOCPD.getBackFeed()));
					}
					if (checkValueTypesService.Equals(PermitProjectSiteInfoEntity.getSecondSolarInterconnection(),
							Integer.toString(updatedBackFeedSolarOCPD.getBackFeed()))) {
						PermitProjectSiteInfoEntity
								.setSecondSolarInterconnection(Integer.toString(backFeedSolarOCPD.getBackFeed()));
					}
					if (checkValueTypesService.Equals(PermitProjectSiteInfoEntity.getThirdSolarInterconnection(),
							Integer.toString(updatedBackFeedSolarOCPD.getBackFeed()))) {
						PermitProjectSiteInfoEntity
								.setThirdSolarInterconnection(Integer.toString(backFeedSolarOCPD.getBackFeed()));
					}
					if (checkValueTypesService.Equals(PermitProjectSiteInfoEntity.getFourthSolarInterconnection(),
							Integer.toString(updatedBackFeedSolarOCPD.getBackFeed()))) {
						PermitProjectSiteInfoEntity
								.setFourthSolarInterconnection(Integer.toString(backFeedSolarOCPD.getBackFeed()));
					}
					if (checkValueTypesService.Equals(PermitProjectSiteInfoEntity.getFifthSolarInterconnection(),
							Integer.toString(updatedBackFeedSolarOCPD.getBackFeed()))) {
						PermitProjectSiteInfoEntity
								.setFifthSolarInterconnection(Integer.toString(backFeedSolarOCPD.getBackFeed()));
					}
				}
			}
			// end
			Date today = new Date();
			SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yyyy");
			String updatedDate = formattedDate.format(today);

			updatedBackFeedSolarOCPD.setId(backFeedSolarOCPD.getId());
			updatedBackFeedSolarOCPD.setBackFeed(backFeedSolarOCPD.getBackFeed());

			updatedBackFeedSolarOCPD.setUpdated(updatedDate);
			backFeedSolarOCPDRepo.save(updatedBackFeedSolarOCPD);
			historicActivity.recordActivity(userID, "", "",
					"libraries;Edit component " + backFeedSolarOCPD.getBackFeed() + ".;Back-Feed Solar OCPD", true, "",
					"", "");

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userID, "", "", "libraries;Edit component.;Back-Feed Solar OCPD", false, "",
					"", "");
			return "fail";
		}
	}

	public String editBackFeedSolarOCPDNotification(Long userID, Long backFeedSolarOCPD) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(userID).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +userID));

			notificationEntityService.addNewNotif(userID, 0L, "Back Feed Solar OCPD Update", "Libraries",
					"Back Feed Solar OCPD Update Update - " + backFeedSolarOCPD,
					"The Back Feed Solar OCPD Update " + backFeedSolarOCPD + " was updated by " + userCo.getFirstName()
							+ " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String addBackFeedSolarOCPDNotification(Long userID, Long backFeed) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(userID).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +userID));

			notificationEntityService
					.addNewNotif(userID, 0L, "New Back Feed Solar OCPD Update", "Libraries",
							"New Back Feed Solar OCPD Update- " + backFeed, "The Back Feed Solar OCPD Update "
									+ backFeed + " was added by " + userCo.getFirstName() + " " + userCo.getLastName(),
							true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	// CR 924
	// Change Request BackFeedSolarOCPD List Management
	public String backFeedSolarOCPDLibraryActived(Long idBackFeedSolarOCPD, Long idUserCo) {
		BackFeedSolarOCPD backFeedSolarOCPDLibrary = new BackFeedSolarOCPD();
		try {
			backFeedSolarOCPDLibrary = backFeedSolarOCPDRepo.findById(idBackFeedSolarOCPD).orElseThrow(
					() -> new ResourceNotFoundException("OCPD not found for this id :" +idBackFeedSolarOCPD));
			if (backFeedSolarOCPDRepo.countByBackFeedAndIsDeleted(backFeedSolarOCPDLibrary.getBackFeed(), false) != 0) {
				return "exist";
			} else {
				backFeedSolarOCPDLibrary.setDeleted(false);
				historicActivity.recordActivity(idUserCo, "", "", "libraries;Activate component "
						+ backFeedSolarOCPDLibrary.getBackFeed() + ".;Back-Feed Solar OCPD", true, "", "", "");
				return "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Activate component .;Back-Feed Solar OCPD",
					false, "", "", "");
			return "false";

		}
	}

	public List<ProjectForLibrariesModel> getAllPermitOfBackFeedSolarOCPDDeleted(Long idBackFeedSolarOCPD) {

		try {
			BackFeedSolarOCPD backFeedSolarOCPDLibrary = backFeedSolarOCPDRepo.findById(idBackFeedSolarOCPD).orElseThrow(
					() -> new ResourceNotFoundException("OCPD not found for this id :" +idBackFeedSolarOCPD));
			return permitProjectSiteInfoEntityRepo
					.findListOfProjectsWithOCPD1(backFeedSolarOCPDLibrary.getBackFeed() + "");
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	public List<PermitEntity> getAllPermitOfBackFeedSolarOCPDDeleted1(Long idBackFeedSolarOCPD) {

		List<PermitEntity> resPermit = new ArrayList<>();
		try {

			BackFeedSolarOCPD backFeedSolarOCPDLibrary = backFeedSolarOCPDRepo.findById(idBackFeedSolarOCPD).orElseThrow(
					() -> new ResourceNotFoundException("OCPD not found for this id :" +idBackFeedSolarOCPD));

			/***************** Get all permit Array content this OCPD *****************/
			List<PermitProjectSiteInfoEntity> allpermitProjectSiteInfo = permitProjectSiteInfoEntityRepo
					.findListOfProjectsWithOCPD(backFeedSolarOCPDLibrary.getBackFeed() + "");

			/************** Get all permit content this model ***************************/

			/****************** test on the state of Permit **************/
			if (backFeedSolarOCPDLibrary.getId() != 0) {
				if (allpermitProjectSiteInfo != null && !allpermitProjectSiteInfo.isEmpty()) {
					for (int i = 0; i < allpermitProjectSiteInfo.size(); i++) {
						if (allpermitProjectSiteInfo.get(i) != null
								&& allpermitProjectSiteInfo.get(i).getPermitEntity() != null) {
							if (checkValueTypesService
									.Equals(allpermitProjectSiteInfo.get(i).getPermitEntity().getStatus(), "Deleted")
									|| checkValueTypesService.Equals(
											allpermitProjectSiteInfo.get(i).getPermitEntity().getStatus(), "Submitted")
									|| checkValueTypesService.Equals(
											allpermitProjectSiteInfo.get(i).getPermitEntity().getStatus(), "Delivered")
									|| checkValueTypesService.Equals(
											allpermitProjectSiteInfo.get(i).getPermitEntity().getIsTemplate(), true)) {
							} else {
								resPermit.add(allpermitProjectSiteInfo.get(i).getPermitEntity());

							}
						}
					}
				}

			} else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resPermit;
	}

	// 03-22-2019: M.A : Junit correction
	public boolean deleteBackFeedSolarOCPDLibrary(Long id, Long idUserCo) {
		try {
			if (id != 0) {
				List<PermitEntity> listPermitDeleted = getAllPermitOfBackFeedSolarOCPDDeleted1(id);
				PermitProjectSiteInfoEntity permitProjectSiteInfo = null;
				BackFeedSolarOCPD backFeedSolarOCPDLibraryRes = backFeedSolarOCPDRepo.findById(id).orElseThrow(
						() -> new ResourceNotFoundException("OCPD not found for this id :" +id));
				if (listPermitDeleted != null) {
					for (PermitEntity permitEntity : listPermitDeleted) {

						permitProjectSiteInfo = permitProjectSiteInfoEntityRepo
								.findByPermitEntityAndSolarInterconnection(permitEntity,
										backFeedSolarOCPDLibraryRes.getBackFeed() + "");
						if (permitProjectSiteInfoEntityRepo.countByPermitEntityAndSolarInterconnection(permitEntity,
								backFeedSolarOCPDLibraryRes.getBackFeed() + "") != 0) {
							permitProjectSiteInfo.setSolarInterconnection("Removed");
						}

						PermitProjectSiteInfoEntity permitProjectSiteInfo2 = permitProjectSiteInfoEntityRepo
								.findByPermitEntityAndSecondSolarInterconnection(permitEntity,
										backFeedSolarOCPDLibraryRes.getBackFeed() + "");
						if (permitProjectSiteInfoEntityRepo.countByPermitEntityAndSecondSolarInterconnection(
								permitEntity, backFeedSolarOCPDLibraryRes.getBackFeed() + "") != 0) {
							permitProjectSiteInfo2.setSecondSolarInterconnection("Removed");
						}

						PermitProjectSiteInfoEntity permitProjectSiteInfo3 = permitProjectSiteInfoEntityRepo
								.findByPermitEntityAndThirdSolarInterconnection(permitEntity,
										backFeedSolarOCPDLibraryRes.getBackFeed() + "");
						if (permitProjectSiteInfoEntityRepo.countByPermitEntityAndThirdSolarInterconnection(
								permitEntity, backFeedSolarOCPDLibraryRes.getBackFeed() + "") != 0) {
							permitProjectSiteInfo3.setThirdSolarInterconnection("Removed");
						}

						PermitProjectSiteInfoEntity permitProjectSiteInfo4 = permitProjectSiteInfoEntityRepo
								.findByPermitEntityAndFourthSolarInterconnection(permitEntity,
										backFeedSolarOCPDLibraryRes.getBackFeed() + "");
						if (permitProjectSiteInfoEntityRepo.countByPermitEntityAndFourthSolarInterconnection(
								permitEntity, backFeedSolarOCPDLibraryRes.getBackFeed() + "") != 0) {
							permitProjectSiteInfo4.setFourthSolarInterconnection("Removed");
						}

						PermitProjectSiteInfoEntity permitProjectSiteInfo5 = permitProjectSiteInfoEntityRepo
								.findByPermitEntityAndFifthSolarInterconnection(permitEntity,
										backFeedSolarOCPDLibraryRes.getBackFeed() + "");
						if (permitProjectSiteInfoEntityRepo.countByPermitEntityAndFifthSolarInterconnection(
								permitEntity, backFeedSolarOCPDLibraryRes.getBackFeed() + "") != 0) {
							permitProjectSiteInfo5.setFifthSolarInterconnection("Removed");
						}

					}
				}

				BackFeedSolarOCPD backFeedSolarOCPDLibrary = backFeedSolarOCPDRepo.findById(id).orElseThrow(
						() -> new ResourceNotFoundException("OCPD not found for this id :" +id));

				backFeedSolarOCPDLibrary.setDeleted(true);
				historicActivity.recordActivity(idUserCo, "", "", "libraries;Delete component "
						+ backFeedSolarOCPDLibrary.getBackFeed() + ".;Back-Feed Solar OCPD", true, "", "", "");

				return true;
			}
			historicActivity.recordActivity(idUserCo, "", "",
					"libraries;Delete component ID " + id + ".;Back-Feed Solar OCPD", false, "", "", "");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "",
					"libraries;Delete component ID " + id + ".;Back-Feed Solar OCPD", false, "", "", "");
			return false;

		}

	}

	public String checkBackFeedSolarOCPDExistent(SearchBackFeedSolarOCPDResult newmodule) {
		try {

			if (backFeedSolarOCPDRepo.countByBackFeedAndIsDeleted(newmodule.getBackFeed(), false) != 0) {

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
	 * Add New BackFeedSolarOCPD
	 */
	public BackFeedSolarOCPDEntityModel addNewBackFeedSolarOCPD(SearchBackFeedSolarOCPDResult backFeedSolarOCPDReq,
			Long idUserCo) {

		try {
			BackFeedSolarOCPD backFeedSolarOCPD = new BackFeedSolarOCPD();
			backFeedSolarOCPD.setBackFeed(backFeedSolarOCPDReq.getBackFeed());
			Date d = new Date();
			SimpleDateFormat formattedDATE = new SimpleDateFormat("MM/dd/yyyy");
			String updatedDate = formattedDATE.format(d);
			backFeedSolarOCPD.setUpdated(updatedDate);

			AuthentificationEntity userCo = authentificationRepo.findById(idUserCo).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +idUserCo));
			backFeedSolarOCPD.setHasSuperUserEdit(userCo.getRoleEntity().getId() == 1 || userCo.getRoleEntity().getId() == 3);
			Date addDate = new Date();
			backFeedSolarOCPD.setAddDate(addDate);

			backFeedSolarOCPD.setAuthentificationEntity(userCo);
			backFeedSolarOCPDRepo.save(backFeedSolarOCPD);
			historicActivity.recordActivity(idUserCo, "", "",
					"libraries;Add component " + backFeedSolarOCPDReq.getBackFeed() + ".;Back-Feed Solar OCPD", true,
					"", "", "");
			 
			return new BackFeedSolarOCPDEntityModel(backFeedSolarOCPD.getId(), backFeedSolarOCPD.getBackFeed(), backFeedSolarOCPD.getUpdated(), backFeedSolarOCPD.isDeleted(),backFeedSolarOCPD.getHasSuperUserEdit() , backFeedSolarOCPD.getAddDate(), backFeedSolarOCPD.getAuthentificationEntity());

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Add component .;Back-Feed Solar OCPD", false,
					"", "", "");
			return new BackFeedSolarOCPDEntityModel();

		}

	}

	public List<BackFeedSolarOCPDEntityModel> getListOfBackFeedSolarOCPD() {
		try {
			return backFeedSolarOCPDRepo.getListOfBackFeedSolarOCPD(false);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

}
