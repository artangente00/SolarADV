package com.PlayGroundAdv.Solar.service.libraries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
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
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.ProposedSubPanel;
import com.PlayGroundAdv.Solar.entity.ProposedSubPanelFavLibraryEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.ProposedSubPanelFavRequest;
import com.PlayGroundAdv.Solar.model.SearchProposedSubPanelResult;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ProposedSubPanelFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ProposedSubPanelRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class SubPanelService {

	final HistoryActivityService historicActivity;
	final NotificationEntityService notifService;
	final CheckValueTypesService checkValue;
	final ProposedSubPanelRepository subPanelRepo;
	final AuthenticationRepository userRepo;
	final ProposedSubPanelFavoritesRepository subPanelFavRepo;
	final PermitRepository permitEntityRepo;
	final PermitProjectSiteInfoRepository siteInfoRepo;
	final EquipmentUpdate mailingService;

	public SubPanelService(HistoryActivityService historicActivity,
			NotificationEntityService notifService, CheckValueTypesService checkValue,
			ProposedSubPanelRepository subPanelRepo, AuthenticationRepository userRepo,
			ProposedSubPanelFavoritesRepository subPanelFavRepo, PermitRepository permitEntityRepo,
			PermitProjectSiteInfoRepository siteInfoRepo, EquipmentUpdate mailingService) {
		super();
		this.historicActivity = historicActivity;
		this.notifService = notifService;
		this.checkValue = checkValue;
		this.subPanelRepo = subPanelRepo;
		this.userRepo = userRepo;
		this.subPanelFavRepo = subPanelFavRepo;
		this.permitEntityRepo = permitEntityRepo;
		this.siteInfoRepo = siteInfoRepo;
		this.mailingService = mailingService;
	}

	public Page<SearchProposedSubPanelResult> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("manufacturer").and(Sort.by("model")));
			String[] manufacturer = checkValue.isStringNotEmpty(request.getManufacturer())
					? request.getManufacturer().split(" ")
					: null;
			String[] model = checkValue.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ") : null;
			if (manufacturer == null && model == null && !Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<ProposedSubPanel> p = subPanelRepo.findByIsDeleted(request.getIsDeleted(), pageable);
				return convertDto(p, pageable, request.getIdUser());
			} else {
				Page<ProposedSubPanel> p = subPanelRepo.findAll(filter(manufacturer, model, request.getIsFavorite(),
						request.getIsDeleted(), request.getIdUser()), pageable);
				return convertDto(p, pageable, request.getIdUser());

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Page<SearchProposedSubPanelResult> convertDto(Page<ProposedSubPanel> page, Pageable pageable, Long userId) {
		try {
			List<Long> favorite = subPanelFavRepo.findFavoriteSubPanel(userId);
			return new PageImpl<>(
					page.stream()
							.map(c -> new SearchProposedSubPanelResult(c.getId(), favorite.indexOf(c.getId()) != -1,
									c.getManufacturer(), c.getModel(), c.getTypeSubPanel(), c.getPolesNumber(),
									c.getNemaRating(), c.getRatedCurrent(), c.getDropdownOption(), c.getSheetFileName(),
									c.getUpdated(), c.isDeleted(),
									new UsersEntityResult(c.getAuthentificationEntity().getId(),
											c.getAuthentificationEntity().getFirstName(),
											c.getAuthentificationEntity().getLastName()),
									c.getHasCorrectionRequest(), c.getEroneousContent(), c.getEroneousContentOther(),
									c.getEroneousDescription(),
									c.getAuthentificationEntity() != null
											? c.getAuthentificationEntity().getFirstName() + " "
													+ c.getAuthentificationEntity().getLastName()
											: "",
									c.getFirstUpdater() != null
											? new UsersEntityResult(c.getFirstUpdater().getId(),
													c.getFirstUpdater().getFirstName(),
													c.getFirstUpdater().getLastName())
											: null,
									c.getSecondUpdater() != null
											? new UsersEntityResult(c.getSecondUpdater().getId(),
													c.getSecondUpdater().getFirstName(),
													c.getSecondUpdater().getLastName())
											: null,
									c.getThirdUpdater() != null ? new UsersEntityResult(c.getThirdUpdater().getId(),
											c.getThirdUpdater().getFirstName(), c.getThirdUpdater().getLastName())
											: null,
									c.getVerifiedBy() != null
											? new UsersEntityResult(c.getVerifiedBy().getId(),
													c.getVerifiedBy().getFirstName(), c.getVerifiedBy().getLastName())
											: null,
									c.getIsVerified(), c.getDateVerification()))
							.collect(Collectors.toList()),
					pageable, page.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Specification<ProposedSubPanel> filter(String[] makes, String[] models, Boolean favorite, Boolean deleted,
			Long userId) {

		return new Specification<ProposedSubPanel>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ProposedSubPanel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicatesMake = new ArrayList<>();
				List<Predicate> predicatesModel = new ArrayList<>();
				for (int i = 0; makes != null && i < makes.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("manufacturer")),
							"%" + makes[i].toLowerCase() + "%");
					predicatesMake.add(predicate);
				}
				for (int i = 0; models != null && i < models.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("model")), "%" + models[i].toLowerCase() + "%");
					predicatesModel.add(predicate);
				}
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				Predicate endPredicateMake = makes != null
						? cb.or(predicatesMake.toArray(new Predicate[predicatesMake.size()]))
						: null;
				Predicate endPredicateModel = models != null
						? cb.or(predicatesModel.toArray(new Predicate[predicatesModel.size()]))
						: null;

				List<Predicate> list = Arrays.asList(predicateDeleted, endPredicateMake, endPredicateModel);
				if (Boolean.TRUE.equals(favorite) && !Boolean.TRUE.equals(deleted)) {
					In<Long> inClause = cb.in(root.get("id"));
					for (Long id : subPanelFavRepo.findFavoriteSubPanel(userId)) {
						inClause.value(id);
					}
					list = Arrays.asList(predicateDeleted, endPredicateMake, endPredicateModel, inClause);
				}
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	public String sendCorrectionProposedSubPanelRequest(CorrectionRequest component) {

		try {
			if (component.getId() != 0) {

				AuthentificationEntity user = userRepo.findById(component.getIdUser()).get();
				ProposedSubPanel library = subPanelRepo.findById(component.getId()).get();
				library.setHasCorrectionRequest(true);
				library.setEroneousContent(component.getEroneousContent());
				library.setEroneousContentOther(component.getEroneousContentOther());
				library.setEroneousDescription(component.getEroneousDescription());
				subPanelRepo.save(library);
				notifService.addNewNotif(component.getIdUser(), 0L, "Request Correction", "Libraries",
						"Request Correction - " + library.getModel(),
						"The user " + user.getFirstName() + " " + user.getLastName()
								+ " request correction for the Proposed Sub Panel " + library.getManufacturer() + " "
								+ library.getModel(),
						true);

			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	/*
	 * Add proposedSubPanelFav
	 */
	public String addProposedSubPanelFavorite(Long idContractor, Long idProposedSubPanel, Long idOwner) {

		try {

			AuthentificationEntity contractor = new AuthentificationEntity();
			ProposedSubPanel proposedSubPanel = new ProposedSubPanel();
			try {
				contractor = userRepo.findById(idOwner).get();
			} catch (Exception e) {
				historicActivity.recordActivity(idContractor, "", "",
						"libraries;Add Sub Panel to Users Favorites List.;Sub Panel", false, "", "", "");
				e.printStackTrace();
			}
			try {
				proposedSubPanel = subPanelRepo.findById(idProposedSubPanel).get();
			} catch (Exception e) {
				historicActivity.recordActivity(idContractor, "", "",
						"libraries;Add Sub Panel to Users Favorites List.;Sub Panel", false, "", "", "");
				e.printStackTrace();
			}
			ProposedSubPanelFavLibraryEntity proposedSubPanelFav = new ProposedSubPanelFavLibraryEntity(contractor,
					proposedSubPanel);
			subPanelFavRepo.save(proposedSubPanelFav);
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;The favorite " + proposedSubPanel.getManufacturer() + " " + proposedSubPanel.getModel()
							+ " is added to the user " + contractor.getFirstName() + " " + contractor.getLastName()
							+ ".;Sub Panel",
					true, "", "", "");
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;Add Sub Panel to user Favorites List;Sub Panel Library", false, "", "", "");
			return "Fail";
		}

	}

	/*
	 * Delete proposedSubPanelFav
	 */
	public String removeProposedSubPanelFavorite(Long idContractor, Long idProposedSubPanel, Long idOwner) {

		try {
			AuthentificationEntity contractor = userRepo.findById(idOwner).get();
			ProposedSubPanelFavLibraryEntity proposedSubPanelFav = subPanelFavRepo
					.findOneByAuthentificationEntityIdAndProposedSubPanelId(idOwner, idProposedSubPanel);
			subPanelFavRepo.delete(proposedSubPanelFav);
			List<PermitProjectSiteInfoEntity> permitArray1 = siteInfoRepo
					.findByPermitEntityAuthentificationEntityIdAndProposedSubPanel(idOwner, idProposedSubPanel + "");
			for (int i = 0; !permitArray1.isEmpty() && i < permitArray1.size(); i++) {
				permitArray1.get(i).setProposedSubPanel("Fav Removed");
				siteInfoRepo.save(permitArray1.get(i));
			}
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;The favorite " + proposedSubPanelFav.getProposedSubPanel().getManufacturer() + " "
							+ proposedSubPanelFav.getProposedSubPanel().getModel() + " is added to the user "
							+ contractor.getFirstName() + " " + contractor.getLastName() + ".;Sub Panel",
					true, "", "", "");
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;Remove Sub Panel from Users Favorites List.;Sub Panel", false, "", "", "");
			return "Fail";
		}

	}

	/*
	 * Get Contractor proposedSubPanelFav
	 */
	public List<ProposedSubPanel> getContractorProposedSubPanelFav(Long idContractor) {

		List<ProposedSubPanel> proposedSubPanelContractorFav = new ArrayList<>();

		try {
			List<Long> proposedSubPanelFavID = subPanelFavRepo.findFavoriteSubPanel(idContractor);
			if (proposedSubPanelFavID != null && !proposedSubPanelFavID.isEmpty()) {
				for (int i = 0; i < proposedSubPanelFavID.size(); i++) {
					ProposedSubPanel proposedSubPanel = new ProposedSubPanel();
					try {
						proposedSubPanel = subPanelRepo.findById(proposedSubPanelFavID.get(i)).get();
					} catch (Exception e) {
						e.printStackTrace();
					}
					proposedSubPanelContractorFav.add(proposedSubPanel);
				}
			} else
				proposedSubPanelContractorFav = null;

			return proposedSubPanelContractorFav;
		} catch (Exception e) {
			e.printStackTrace();
			return proposedSubPanelContractorFav;
		}
	}

	/*
	 * Get Contractor proposedSubPanelFav
	 */
	public List<ProposedSubPanel> getSuperUserProposedSubPanelFav(Long permitId) {

		List<ProposedSubPanel> proposedSubPanelContractorFav = new ArrayList<>();
		try {
			PermitEntity permit = permitEntityRepo.findById(permitId).get();
			Long idUser = permit.getAuthentificationEntity().getId();

			List<Long> proposedSubPanelFavID = subPanelFavRepo.findFavoriteSubPanel(idUser);
			if (proposedSubPanelFavID != null && !proposedSubPanelFavID.isEmpty()) {
				for (int i = 0; i < proposedSubPanelFavID.size(); i++) {
					ProposedSubPanel proposedSubPanel = new ProposedSubPanel();
					try {
						proposedSubPanel = subPanelRepo.findById(proposedSubPanelFavID.get(i)).get();
					} catch (Exception e) {
						e.printStackTrace();
					}
					proposedSubPanelContractorFav.add(proposedSubPanel);
				}
			} else
				proposedSubPanelContractorFav = null;

			return proposedSubPanelContractorFav;
		} catch (Exception e) {
			e.printStackTrace();
			return proposedSubPanelContractorFav;
		}
	}

	public boolean getTestProposedSubPanelFav(Long permitId, String testMan) {

		try {
			int test = 0;
			PermitEntity permit = permitEntityRepo.findById(permitId).get();
			Long idUser = permit.getAuthentificationEntity().getId();

			List<ProposedSubPanel> proposedSubPanelContractorFav = new ArrayList<ProposedSubPanel>();

			List<Long> proposedSubPanelFavID = subPanelFavRepo.findFavoriteSubPanel(idUser);

			if (proposedSubPanelFavID != null) {
				for (int i = 0; i < proposedSubPanelFavID.size(); i++) {
					ProposedSubPanel proposedSubPanel = new ProposedSubPanel();
					try {
						proposedSubPanel = subPanelRepo.findById(proposedSubPanelFavID.get(i)).get();
					} catch (Exception e) {
						e.printStackTrace();
					}
					proposedSubPanelContractorFav.add(proposedSubPanel);
				}
			}

			for (int i = 0; i < proposedSubPanelContractorFav.size(); i++) {
				if (proposedSubPanelContractorFav.get(i) != null
						&& checkValue.Equals(testMan, proposedSubPanelContractorFav.get(i).getManufacturer() + ":"
								+ proposedSubPanelContractorFav.get(i).getModel())) {
					test = 1;
				}
			}

			if (test != 1) {
				return true;
			} else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/*
	 * Add proposedSubPanel
	 */
	public String addProposedSubPanel(Long IdContractor, ProposedSubPanelFavRequest proposedSubPanelReq, String ipUser,
			String timeZoneUser, Long idUser) {

		try {

			List<String> proposedSubPanels = subPanelRepo
					.findModelAndManufacturer(proposedSubPanelReq.getManufacturer(), proposedSubPanelReq.getModel());
			if (proposedSubPanels.isEmpty()) {
				ProposedSubPanel proposedSubPanel = new ProposedSubPanel();
				proposedSubPanel.setManufacturer(proposedSubPanelReq.getManufacturer());
				proposedSubPanel.setModel(proposedSubPanelReq.getModel());
				proposedSubPanel.setTypeSubPanel(proposedSubPanelReq.getTypeSubPanel());
				proposedSubPanel.setPolesNumber(proposedSubPanelReq.getPolesNumber());
				proposedSubPanel.setNemaRating(proposedSubPanelReq.getNemaRating());
				proposedSubPanel.setRatedCurrent(proposedSubPanelReq.getRatedCurrent());
				proposedSubPanel.setDropdownOption(proposedSubPanelReq.getDropdownOption());
				proposedSubPanel.setSheetFileName(proposedSubPanelReq.getSheetFileName());
				proposedSubPanel.setHasCorrectionRequest(proposedSubPanelReq.getHasCorrectionRequest());
				proposedSubPanel.setEroneousContent(proposedSubPanelReq.getEroneousContent());
				proposedSubPanel.setEroneousContentOther(proposedSubPanelReq.getEroneousContentOther());
				proposedSubPanel.setEroneousDescription(proposedSubPanelReq.getEroneousDescription());
				Date d = new Date();
				SimpleDateFormat formattedDATE = new SimpleDateFormat("MM/dd/yyyy");
				String updatedDate = formattedDATE.format(d);
				proposedSubPanel.setUpdated(updatedDate);

				AuthentificationEntity contractor = new AuthentificationEntity();
				try {
					contractor = userRepo.findById(IdContractor).get();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ProposedSubPanelFavLibraryEntity proposedSubPanelFav = new ProposedSubPanelFavLibraryEntity(contractor,
						proposedSubPanel);
				proposedSubPanelFav.setAuthentificationEntity(contractor);
				proposedSubPanelFav.setProposedSubPanel(proposedSubPanel);
				proposedSubPanel.setAuthentificationEntity(contractor);
				proposedSubPanel.setIsVerified(false);
				if (contractor.getRoleEntity().getId() == 1 || contractor.getRoleEntity().getId() == 3) {
					proposedSubPanel.setHasSuperUserEdit(true);
				} else {
					proposedSubPanel.setHasSuperUserEdit(false);
				}
				subPanelFavRepo.save(proposedSubPanelFav);
				subPanelRepo.save(proposedSubPanel);
				mailingService.mailUpdate("Sub Panel",
						"A new equipment of Sub Panel " + proposedSubPanel.getManufacturer() + " "
								+ proposedSubPanel.getManufacturer() + " has been added by " + contractor.getFirstName()
								+ " " + contractor.getLastName(),
						contractor.getEmail().contains("nuagetechnologies-tn.com")
								&& !contractor.getEmail().contains("pm"));
				historicActivity.recordActivity(idUser, ipUser, timeZoneUser, "libraries;Add component "
						+ proposedSubPanel.getManufacturer() + " " + proposedSubPanel.getModel() + ".;Sub Panel", true,
						"", "", "");
				return "Done";
			} else
				historicActivity.recordActivity(idUser, ipUser, timeZoneUser,
						"Add ProposedSubPanel Component Library;ProposedSubPanel already exists in data sources;Add failed ",
						false, "", "", "");
			return "ProposedSubPanel already exists in data sources";

		} catch (Exception e) {
			historicActivity.recordActivity(idUser, ipUser, timeZoneUser, "libraries;Add component.;Sub Panel", false,
					"", "", "");
			e.printStackTrace();
			return "error";
		}
	}

	/*
	 * get Total Number of ProposedSubPanel ( function
	 * "Long getNumProposedSubPanelTot() " removed )
	 */

	/*
	 * edit ProposedSubPanel
	 */
	public String editProposedSubPanel(ProposedSubPanelFavRequest proposedSubPanel, Long userID) {
		try {

			List<ProposedSubPanel> listProposedSubPanel = subPanelRepo
					.findAllByManufacturerAndModelAndIsDeletedAndIdNot(
							proposedSubPanel.getManufacturer().trim().toLowerCase(),
							proposedSubPanel.getModel().trim().toLowerCase(), false, proposedSubPanel.getId());
			if (listProposedSubPanel != null && !listProposedSubPanel.isEmpty()) {
				return "exist";
			} else {
				return editProposedSubPanelFunction(proposedSubPanel, userID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String editProposedSubPanelFunction(ProposedSubPanelFavRequest proposedSubPanel, Long userID) {

		try {
			AuthentificationEntity firstUpdater = userRepo.findById(userID)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + userID));

			ProposedSubPanel updatedProposedSubPanel = subPanelRepo.findById(proposedSubPanel.getId()).get();
			// manufacturer or model has change ---> update (manufacturer,model) in all the
			// project using the edited proposedSubPanel
			List<PermitProjectSiteInfoEntity> PermitProjectSiteInfoEntitys = siteInfoRepo
					.findByProposedSubPanel(Long.toString(updatedProposedSubPanel.getId()));
			if (PermitProjectSiteInfoEntitys != null && !PermitProjectSiteInfoEntitys.isEmpty()) {
				for (PermitProjectSiteInfoEntity PermitProjectSiteInfoEntity : PermitProjectSiteInfoEntitys) {
					if (PermitProjectSiteInfoEntity != null
							&& PermitProjectSiteInfoEntity.getProposedSubPanel() != null) {
						PermitProjectSiteInfoEntity.setProposedSubPanel(Long.toString(proposedSubPanel.getId()));
					}

				}
			}
			// end
			updatedProposedSubPanel.setId(proposedSubPanel.getId());
			updatedProposedSubPanel.setManufacturer(proposedSubPanel.getManufacturer());
			updatedProposedSubPanel.setModel(proposedSubPanel.getModel());
			updatedProposedSubPanel.setTypeSubPanel(proposedSubPanel.getTypeSubPanel());
			updatedProposedSubPanel.setPolesNumber(proposedSubPanel.getPolesNumber());
			updatedProposedSubPanel.setNemaRating(proposedSubPanel.getNemaRating());
			updatedProposedSubPanel.setRatedCurrent(proposedSubPanel.getRatedCurrent());
			updatedProposedSubPanel.setDropdownOption(proposedSubPanel.getDropdownOption());
			updatedProposedSubPanel.setSheetFileName(proposedSubPanel.getSheetFileName());
			updatedProposedSubPanel.setHasCorrectionRequest(proposedSubPanel.getHasCorrectionRequest());
			updatedProposedSubPanel.setEroneousContent(proposedSubPanel.getEroneousContent());
			updatedProposedSubPanel.setEroneousContentOther(proposedSubPanel.getEroneousContentOther());
			updatedProposedSubPanel.setEroneousDescription(proposedSubPanel.getEroneousDescription());
			Date d = new Date();
			SimpleDateFormat formattedDATE = new SimpleDateFormat("MM/dd/yyyy");
			String updatedDate = formattedDATE.format(d);
			updatedProposedSubPanel.setUpdated(updatedDate);

			updatedProposedSubPanel.setIsVerified(false);
			String updateNum = "";
			if (updatedProposedSubPanel.getFirstUpdater() == null) {
				updateNum = "1st";
				updatedProposedSubPanel.setFirstUpdater(firstUpdater);
			} else if (updatedProposedSubPanel.getSecondUpdater() == null) {
				updateNum = "2nd";
				updatedProposedSubPanel.setSecondUpdater(firstUpdater);
			} else if (updatedProposedSubPanel.getThirdUpdater() == null) {
				updateNum = "3rd";
				updatedProposedSubPanel.setThirdUpdater(firstUpdater);
			}

			subPanelRepo.save(updatedProposedSubPanel);
			historicActivity.recordActivity(userID, "", "", "libraries;Edit component "
					+ proposedSubPanel.getManufacturer() + " " + proposedSubPanel.getModel() + ".;Sub Panel", true, "",
					"", "");

			if (checkValue.isStringNotEmpty(updateNum)) {
				mailingService.mailUpdate("Sub Panel",
						"An existing equipment of Sub Panel " + proposedSubPanel.getManufacturer() + " "
								+ proposedSubPanel.getModel() + " has been updated " + updateNum + " time by "
								+ firstUpdater.getFirstName() + " " + firstUpdater.getLastName(),
						firstUpdater.getEmail().contains("nuagetechnologies-tn.com")
								&& !firstUpdater.getEmail().contains("pm"));
			}

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userID, "", "", "libraries;Edit component.;Sub Panel", false, "", "", "");
			return "fail";
		}
	}

	public String editProposedSubPanelNotification(Long userID, String proposedSubPanelManufacturer,
			String proposedSubPanelModel) {
		try {

			AuthentificationEntity userCo = userRepo.findById(userID).get();

			notifService.addNewNotif(userID, 0L, "Proposed Sub Panel Update", "Libraries",
					"Proposed Sub Panel Update - " + proposedSubPanelModel,
					"The proposed Sub Panel " + proposedSubPanelModel + " " + proposedSubPanelManufacturer
							+ " was updated by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String addProposedSubPanelNotification(Long userID, String proposedSubPanelManufacturer,
			String proposedSubPanelModel) {
		try {

			AuthentificationEntity userCo = userRepo.findById(userID).get();

			notifService.addNewNotif(userID, 0L, "New Proposed Sub Panel", "Libraries",
					"New Proposed Sub Panel- " + proposedSubPanelModel,
					"The proposed Sub Panel " + proposedSubPanelModel + " " + proposedSubPanelManufacturer
							+ " was added by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}
	// CR 924
	// Change Request ProposedSubPanel List Management

	public String proposedSubPanelLibraryActived(Long idProposedSubPanel, Long userID) {

		try {

			ProposedSubPanel proposedSubPanelLibrary = subPanelRepo.findById(idProposedSubPanel).get();
			List<ProposedSubPanel> listProposedSubPanel = subPanelRepo.findAllByManufacturerAndModelAndIsDeleted(
					proposedSubPanelLibrary.getManufacturer().trim().toLowerCase(),
					proposedSubPanelLibrary.getModel().trim().toLowerCase(), false);

			if (proposedSubPanelLibrary.getId() != 0) {
				if (listProposedSubPanel != null && !listProposedSubPanel.isEmpty()) {
					return "exist";
				} else {
					proposedSubPanelLibrary.setDeleted(false);
					historicActivity.recordActivity(userID, "", "",
							"libraries;Activate component " + proposedSubPanelLibrary.getManufacturer() + " "
									+ proposedSubPanelLibrary.getModel() + ".;Sub Panel",
							true, "", "", "");
					return "true";
				}

			}
			historicActivity.recordActivity(userID, "", "",
					"libraries;Activate component " + proposedSubPanelLibrary.getManufacturer() + " "
							+ proposedSubPanelLibrary.getModel() + ".;Sub Panel",
					false, "", "", "");
			return "false";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userID, "", "", "libraries;Activate component.;Sub Panel", false, "", "",
					"");
			return "false";
		}
	}

	public List<ProjectForLibrariesModel> getAllPermitOfProposedSubPanelDeleted(Long idProposedSubPanel) {

		try {
			return siteInfoRepo.findByProposedSubPanel1(idProposedSubPanel + "");
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public List<PermitEntity> getAllPermitOfProposedSubPanelDeleted1(Long idProposedSubPanel) {
		List<PermitEntity> resPermit = new ArrayList<>();
		try {
			ProposedSubPanel proposedSubPanelLibrary = subPanelRepo.findById(idProposedSubPanel).get();

			/***************** Get all permit Array content this model *****************/
			List<PermitProjectSiteInfoEntity> allpermitProjectSiteInfo = siteInfoRepo
					.findByProposedSubPanel(proposedSubPanelLibrary.getId() + "");

			/************** Get all permit content this model ***************************/

			/****************** test on the state of Permit **************/
			if (proposedSubPanelLibrary.getId() != 0) {
				if (allpermitProjectSiteInfo != null && !allpermitProjectSiteInfo.isEmpty()) {
					for (int i = 0; i < allpermitProjectSiteInfo.size(); i++) {
						if (allpermitProjectSiteInfo.get(i) != null
								&& allpermitProjectSiteInfo.get(i).getPermitEntity() != null) {
							if (checkValue.Equals(allpermitProjectSiteInfo.get(i).getPermitEntity().getStatus(),
									"Deleted")
									|| checkValue.Equals(allpermitProjectSiteInfo.get(i).getPermitEntity().getStatus(),
											"Submitted")
									|| checkValue.Equals(allpermitProjectSiteInfo.get(i).getPermitEntity().getStatus(),
											"Delivered")
									|| checkValue.Equals(
											allpermitProjectSiteInfo.get(i).getPermitEntity().getIsTemplate(), true)) {
							} else {
								resPermit.add(allpermitProjectSiteInfo.get(i).getPermitEntity());

							}
						}
					}
				}

			} else
				return null;

			return resPermit;
		} catch (Exception e) {
			e.printStackTrace();
			return resPermit;
		}
	}

	public boolean deleteProposedSubPanelLibrary(Long id, Long userID) {

		try {
			ProposedSubPanel proposedSubPanelLibrary = subPanelRepo.findById(id).get();
			if (id != 0) {
				List<PermitEntity> listPermitDeleted = getAllPermitOfProposedSubPanelDeleted1(id);
				PermitProjectSiteInfoEntity permitProjectSiteInfo = null;
				ProposedSubPanel proposedSubPanelLibraryRes = subPanelRepo.findById(id).get();
				if (listPermitDeleted != null) {
					for (PermitEntity permitEntity : listPermitDeleted) {
						if (siteInfoRepo.countByPermitEntityAndProposedSubPanel(permitEntity,
								proposedSubPanelLibraryRes.getId() + "") != 0) {
							permitProjectSiteInfo = siteInfoRepo.findByPermitEntityAndProposedSubPanel(permitEntity,
									proposedSubPanelLibraryRes.getId() + "");
							permitProjectSiteInfo.setProposedSubPanel("Removed");
						}
					}
				}
				proposedSubPanelLibrary.setDeleted(true);
				List<ProposedSubPanelFavLibraryEntity> proposedSubPanelFav = subPanelFavRepo
						.findAllByProposedSubPanelId(id);
				if (proposedSubPanelFav != null && !proposedSubPanelFav.isEmpty()) {
					for (ProposedSubPanelFavLibraryEntity ProposedSubPanelFavLibraryEntity : proposedSubPanelFav) {
						subPanelFavRepo.delete(ProposedSubPanelFavLibraryEntity);
					}
				}

				historicActivity.recordActivity(userID, "", "",
						"libraries;Delete component " + proposedSubPanelLibrary.getManufacturer() + " "
								+ proposedSubPanelLibrary.getModel() + ".;Sub Panel",
						true, "", "", "");
				return true;

			}
			historicActivity.recordActivity(userID, "", "",
					"libraries;Delete component " + proposedSubPanelLibrary.getManufacturer() + " "
							+ proposedSubPanelLibrary.getModel() + " .;Sub Panel",
					false, "", "", "");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userID, "", "", "libraries;Delete component.;Sub Panel", false, "", "", "");
			return false;
		}

	}

	/*
	 * Edit ProposedSubPanel Favorite for Other Users
	 */

	public List<UsersEntityResult> getUsersForFavList(Long idProposedSubPanel, Long userID) {

		List<UsersEntityResult> usersList = new ArrayList<>();
		try {
			List<ProposedSubPanelFavLibraryEntity> proposedSubPanelFav = subPanelFavRepo
					.findAllByProposedSubPanelId(idProposedSubPanel);
			if (!proposedSubPanelFav.isEmpty()) {

				List<Long> usersFavID = new ArrayList<>();
				for (int i = 0; i < proposedSubPanelFav.size(); i++) {
					if (proposedSubPanelFav.get(i) != null
							&& proposedSubPanelFav.get(i).getAuthentificationEntity() != null) {
						usersFavID.add(proposedSubPanelFav.get(i).getAuthentificationEntity().getId());
					}
				}

				usersList = userRepo.findUserHaveNotFav(usersFavID, false, true, userID);

			} else {

				usersList = userRepo.findUserHaveNotFav(null, false, true, userID);
			}
			return usersList;

		} catch (Exception e) {
			e.printStackTrace();
			return usersList;
		}

	}

	public String editUsersFavoriteList(Long idProposedSubPanel, Long[] listUsers, String ipUser, String timeZoneUser,
			Long idUserCo) {
		try {

			ProposedSubPanel proposedSubPanel = subPanelRepo.findById(idProposedSubPanel).get();
			for (int i = 0; i < listUsers.length; i++) {

				AuthentificationEntity user = userRepo.findById(listUsers[i]).get();

				ProposedSubPanelFavLibraryEntity proposedSubPanelFav = new ProposedSubPanelFavLibraryEntity(user,
						proposedSubPanel);
				subPanelFavRepo.save(proposedSubPanelFav);

				historicActivity.recordActivity(idUserCo, "", "",
						"libraries;The favorite " + proposedSubPanel.getManufacturer() + " "
								+ proposedSubPanel.getModel() + " is added to the user " + user.getFirstName() + " "
								+ user.getLastName() + ".;Sub Panel",
						true, "", "", "");
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add Sub Panel to Users Favorites List.;Sub Panel", false, "", "", "");
			return "error";
		}
	}

	public List<ProposedSubPanelFavRequest> checkProposedSubPanelExistent(ProposedSubPanelFavRequest newmodule,
			Long idUser) {
		List<ProposedSubPanelFavRequest> proposedSubPanelList = new ArrayList<ProposedSubPanelFavRequest>();
		try {
			List<ProposedSubPanel> listProposedSubPanel = subPanelRepo.findAllByManufacturerAndModelAndIsDeleted(
					newmodule.getManufacturer().trim().toLowerCase(), newmodule.getModel().trim().toLowerCase(), false);
			List<ProposedSubPanel> listProposedSubPanelNot = subPanelRepo.findAllByManufacturerNotAndModelAndIsDeleted(
					newmodule.getManufacturer().trim().toLowerCase(), newmodule.getModel().trim().toLowerCase(), false);

			if (listProposedSubPanel != null && !listProposedSubPanel.isEmpty()) {
				List<Long> ProposedSubPanelFavID = subPanelFavRepo.findFavoriteSubPanel(idUser);

				for (int i = 0; i < listProposedSubPanel.size(); i++) {

					ProposedSubPanelFavRequest proposedSubPanel = new ProposedSubPanelFavRequest();
					if (listProposedSubPanel.get(i) != null) {
						if (ProposedSubPanelFavID != null
								&& ProposedSubPanelFavID.contains(listProposedSubPanel.get(i).getId())) {
							proposedSubPanel.setIsFav("true");
						} else {
							proposedSubPanel.setIsFav("false");
						}
						proposedSubPanel.setId(listProposedSubPanel.get(i).getId());
						proposedSubPanel.setManufacturer(listProposedSubPanel.get(i).getManufacturer());
						proposedSubPanel.setModel(listProposedSubPanel.get(i).getModel());
					}
					proposedSubPanelList.add(i, proposedSubPanel);
				}
				return proposedSubPanelList;

			} else if (listProposedSubPanelNot != null && !listProposedSubPanelNot.isEmpty()) {

				List<Long> ProposedSubPanelFavID = subPanelFavRepo.findFavoriteSubPanel(idUser);

				for (int i = 0; i < listProposedSubPanelNot.size(); i++) {

					ProposedSubPanelFavRequest proposedSubPanel = new ProposedSubPanelFavRequest();
					if (listProposedSubPanelNot.get(i) != null) {
						if (ProposedSubPanelFavID != null
								&& ProposedSubPanelFavID.contains(listProposedSubPanelNot.get(i).getId())) {
							proposedSubPanel.setIsFav("true");
						} else {
							proposedSubPanel.setIsFav("false");
						}
						proposedSubPanel.setId(listProposedSubPanelNot.get(i).getId());
						proposedSubPanel.setManufacturer(listProposedSubPanelNot.get(i).getManufacturer());
						proposedSubPanel.setModel(listProposedSubPanelNot.get(i).getModel());
					}
					proposedSubPanelList.add(i, proposedSubPanel);
				}
				return proposedSubPanelList;

			} else {
				return proposedSubPanelList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return proposedSubPanelList;
		}

	}

	/*
	 * Add New ProposedSubPanel
	 */
	public ProposedSubPanel addNewProposedSubPanel(Long idPermitInfo, ProposedSubPanelFavRequest proposedSubPanelReq,
			Long idUserCo) {

		ProposedSubPanel proposedSubPanel = new ProposedSubPanel();

		try {

			proposedSubPanel.setManufacturer(proposedSubPanelReq.getManufacturer());
			proposedSubPanel.setModel(proposedSubPanelReq.getModel());
			proposedSubPanel.setTypeSubPanel(proposedSubPanelReq.getTypeSubPanel());
			proposedSubPanel.setPolesNumber(proposedSubPanelReq.getPolesNumber());
			proposedSubPanel.setNemaRating(proposedSubPanelReq.getNemaRating());
			proposedSubPanel.setRatedCurrent(proposedSubPanelReq.getRatedCurrent());
			proposedSubPanel.setDropdownOption(proposedSubPanelReq.getDropdownOption());
			proposedSubPanel.setSheetFileName(proposedSubPanelReq.getSheetFileName());
			Date d = new Date();
			SimpleDateFormat formattedDATE = new SimpleDateFormat("MM/dd/yyyy");
			String updatedDate = formattedDATE.format(d);
			proposedSubPanel.setUpdated(updatedDate);

			AuthentificationEntity contractor = permitEntityRepo.findAuthentificationEntityByID(idPermitInfo);

			AuthentificationEntity userCo = userRepo.findById(idUserCo).get();

			if (userCo.getRoleEntity().getId() == 1 || userCo.getRoleEntity().getId() == 3) {
				proposedSubPanel.setHasSuperUserEdit(true);
			} else {
				proposedSubPanel.setHasSuperUserEdit(false);
			}
			Date addDate = new Date();
			proposedSubPanel.setAddDate(addDate);
			ProposedSubPanelFavLibraryEntity proposedSubPanelFav = new ProposedSubPanelFavLibraryEntity(contractor,
					proposedSubPanel);
			proposedSubPanel.setAuthentificationEntity(userCo);
			subPanelFavRepo.save(proposedSubPanelFav);
			subPanelRepo.save(proposedSubPanel);
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Add component "
					+ proposedSubPanelReq.getManufacturer() + " " + proposedSubPanelReq.getModel() + ".;Sub Panel",
					true, "", "", "");
			return proposedSubPanel;

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Add component.;Sub Panel", true, "", "", "");
			return proposedSubPanel;

		}

	}

}
