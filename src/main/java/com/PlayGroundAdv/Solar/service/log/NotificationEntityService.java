package com.PlayGroundAdv.Solar.service.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.NotificationEntity;
import com.PlayGroundAdv.Solar.entity.UserNoticationsEntity;
import com.PlayGroundAdv.Solar.model.NotificationRequest;
import com.PlayGroundAdv.Solar.model.NotificationResponseModel;
import com.PlayGroundAdv.Solar.repositories.NotificationRepository;
import com.PlayGroundAdv.Solar.repositories.UserNoticationsRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class NotificationEntityService {

	final CheckValueTypesService checkValueTypesService;
	final UserNoticationsRepository userNoticationsRepo;
	final NotificationRepository notificationRepo;
	final AuthenticationRepository userRepo;

	public NotificationEntityService(CheckValueTypesService checkValueTypesService,
			UserNoticationsRepository userNoticationsRepo, NotificationRepository notificationRepo,
			AuthenticationRepository userRepo) {
		super();
		this.checkValueTypesService = checkValueTypesService;
		this.userNoticationsRepo = userNoticationsRepo;
		this.notificationRepo = notificationRepo;
		this.userRepo = userRepo;
	}

	public NotificationResponseModel getThirtyUnreadNotification(Long idUserConnect) {

		try {
			Pageable pageable = PageRequest.of(0, 10);
			Page<UserNoticationsEntity> pageProjects = userNoticationsRepo
					.findByIdUserIdAndIsReadAndIdNotifTitleOrderByIdNotifDateNotifDesc(idUserConnect, false, "Projects",
							pageable);
			Page<UserNoticationsEntity> pageLibraries = userNoticationsRepo
					.findByIdUserIdAndIsReadAndIdNotifTitleOrderByIdNotifDateNotifDesc(idUserConnect, false,
							"Libraries", pageable);
			Page<UserNoticationsEntity> pageAccounts = userNoticationsRepo
					.findByIdUserIdAndIsReadAndIdNotifTitleOrderByIdNotifDateNotifDesc(idUserConnect, false, "Accounts",
							pageable);
			return getNotificationPage(pageable, pageProjects, pageLibraries, pageAccounts);
		} catch (Exception e) {
			e.printStackTrace();
			return new NotificationResponseModel();
		}

	}

	public NotificationResponseModel getThirtyNotification(Long idUserConnect) {
		try {
			Pageable pageable = PageRequest.of(0, 10);
			Page<UserNoticationsEntity> pageProjects = userNoticationsRepo
					.findByIdUserIdAndIdNotifTitleOrderByIdNotifDateNotifDesc(idUserConnect, "Projects", pageable);
			Page<UserNoticationsEntity> pageLibraries = userNoticationsRepo
					.findByIdUserIdAndIdNotifTitleOrderByIdNotifDateNotifDesc(idUserConnect, "Libraries", pageable);
			Page<UserNoticationsEntity> pageAccounts = userNoticationsRepo
					.findByIdUserIdAndIdNotifTitleOrderByIdNotifDateNotifDesc(idUserConnect, "Accounts", pageable);
			return getNotificationPage(pageable, pageProjects, pageLibraries, pageAccounts);
		} catch (Exception e) {
			e.printStackTrace();
			return new NotificationResponseModel();
		}
	}

	private NotificationResponseModel getNotificationPage(Pageable pageable, Page<UserNoticationsEntity> pageProjects,
			Page<UserNoticationsEntity> pageLibraries, Page<UserNoticationsEntity> pageAccounts) {
		try {
			NotificationResponseModel listNotifications = new NotificationResponseModel();
			listNotifications.setProjectsRequest(convertDtoList(pageProjects));
			listNotifications.setLibrariesRequest(convertDtoList(pageLibraries));
			listNotifications.setAccountsRequest(convertDtoList(pageAccounts));
			listNotifications.setProjectsRequestSize(pageProjects.getTotalElements());
			listNotifications.setLibrariesRequestSize(pageLibraries.getTotalElements());
			listNotifications.setAccountsRequestSize(pageAccounts.getTotalElements());
			return listNotifications;
		} catch (Exception e) {
			e.printStackTrace();
			return new NotificationResponseModel();
		}
	}
	// ************get only 10 unread notification****************

	public List<NotificationRequest> getTenUnreadNotifications(Long idUser, int nbPage, String type) {
		try {
			Pageable pageable = PageRequest.of(nbPage / 10, 10);
			Page<UserNoticationsEntity> pageProjects = userNoticationsRepo
					.findByIdUserIdAndIsReadAndIdNotifTitleOrderByIdNotifDateNotifDesc(idUser, false, type, pageable);
			return convertDtoList(pageProjects);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	// ************get only 10 notification****************

	public List<NotificationRequest> getTenNotifications(Long idUser, int nbPage, String type) {
		try {
			Pageable pageable = PageRequest.of(nbPage / 10, 10);
			Page<UserNoticationsEntity> pageProjects = userNoticationsRepo
					.findByIdUserIdAndIdNotifTitleOrderByIdNotifDateNotifDesc(idUser, type, pageable);
			return convertDtoList(pageProjects);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	// ************Converter****************
	private List<NotificationRequest> convertDtoList(Page<UserNoticationsEntity> page) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			if (page != null && !page.getContent().isEmpty()) {
				return page.getContent().stream()
						.map(notification -> new NotificationRequest(notification.getIdNotif().getId(),
								notification.getIdNotif().getTitle(),
								notification.getIdUser().getFirstName() + " " + notification.getIdUser().getLastName(),
								dateFormat.format(notification.getIdNotif().getDateNotif()),
								notification.getIdNotif().getSubject(), notification.getIdNotif().getMessage(),
								Boolean.TRUE.equals(notification.getIsRead()) ? "read" : "unread"))
						.collect(Collectors.toList());

			}
			return new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public String setNotifRead(Long idUserConnect, Long idNotif) {

		try {
			List<UserNoticationsEntity> listNotif = userNoticationsRepo.findByIdUserIdAndIdNotifId(idUserConnect,
					idNotif);
			listNotif.get(0).setIsRead(true);
			userNoticationsRepo.save(listNotif.get(0));

			if (listNotif.isEmpty()) {
				NotificationEntity notif = notificationRepo.findByIdOrderByDateNotif(idNotif);
				notif.setIsShowen(true);
				notificationRepo.save(notif);
			}

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	public List<AuthentificationEntity> getAllSuperUsers() {
		List<AuthentificationEntity> userCo = new ArrayList<AuthentificationEntity>();
		try {

			userCo = userRepo.findByRoleEntityId(1L);
			return userCo;
		} catch (Exception e) {
			e.printStackTrace();
			return userCo;
		}

	}

	public String addNewNotif(Long idUserConnect, Long idUserReceiver, String typeNotif, String title, String subject,
			String msg, Boolean sendToSupserUser) {

		try {

			Date dateNotif = new Date();
			SimpleDateFormat FormattedDATE = new SimpleDateFormat("MM/dd/yyyy 'at' hh:mm");
			LocalDateTime now = LocalDateTime.now();
			int year = now.getYear();
			int month = now.getMonthValue();
			int day = now.getDayOfMonth();
			int hour = now.getHour();
			int minute = now.getMinute();

			AuthentificationEntity userCo = (AuthentificationEntity) userRepo.findById(idUserConnect).get();

			NotificationEntity Notification = new NotificationEntity();

			Notification.setIsShowen(false);
			Notification.setTypeNotif(typeNotif);
			Notification.setTitle(title);
			if (checkValueTypesService.NotEquals(userCo.getCompany(), "")) {
				Notification.setUserName(userCo.getCompany());
			} else {
				Notification.setUserName(userCo.getFirstName() + " " + userCo.getLastName());
			}
			Notification.setSubject(subject);
			Notification.setMessage(msg);
			Notification.setDateNotif(FormattedDATE.parse(FormattedDATE.format(dateNotif)));
			Notification.setHeur(hour + ":" + minute);
			Notification.setJour(year + "/" + month + "/" + day);
			notificationRepo.save(Notification);

			if (checkValueTypesService.Equals(sendToSupserUser, true)) {
				List<AuthentificationEntity> superUsers = getAllSuperUsers();
				for (int i = 0; superUsers != null && i < superUsers.size(); i++) {
					try {
						UserNoticationsEntity userNotification = new UserNoticationsEntity();
						userNotification.setIdNotif(Notification);
						userNotification.setIdUser(superUsers.get(i));
						userNotification.setIsRead(false);
						userNoticationsRepo.save(userNotification);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			} else {
				try {

					AuthentificationEntity userReceiver = (AuthentificationEntity) userRepo.findById(idUserReceiver)
							.get();

					UserNoticationsEntity userNotification = new UserNoticationsEntity();
					userNotification.setIdNotif(Notification);
					userNotification.setIdUser(userReceiver);
					userNotification.setIsRead(false);
					userNoticationsRepo.save(userNotification);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			return "Success";

		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	public String requestComponentCorrectionNotif(Long idUserConnect, String Component, String ComponentType) {
		try {

			AuthentificationEntity user = new AuthentificationEntity();
			NotificationEntity notif = new NotificationEntity();

			if (idUserConnect != null) {
				user = userRepo.findById(idUserConnect).get();
				if (user != null) {
					notif.setUserNotif(user);
				}
			}

			// get date time current

			Date dateNotif = new Date();
			SimpleDateFormat FormattedDATE = new SimpleDateFormat("MM/dd/yyyy 'at' hh:mm");
			LocalDateTime now = LocalDateTime.now();
			int year = now.getYear();
			int month = now.getMonthValue();
			int day = now.getDayOfMonth();
			int hour = now.getHour();
			int minute = now.getMinute();

			notif.setIsShowen(false);
			notif.setTypeNotif("requestCorrection");
			notif.setMessage("The user " + user.getFirstName() + " " + user.getLastName()
					+ " request correction for the " + ComponentType + " " + Component + ".");
			notif.setDateNotif(FormattedDATE.parse(FormattedDATE.format(dateNotif)));
			notif.setHeur(hour + ":" + minute);
			notif.setJour(year + "/" + month + "/" + day);
			notificationRepo.save(notif);

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

//05-14-2019 :M.A : CR-2676 Add Read ALL Notification option
	public String makeAllActivitesRead(Long idUserConnect) {

		try {
			List<UserNoticationsEntity> list = userNoticationsRepo.findByIdUserIdAndIsRead(idUserConnect, false);
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					UserNoticationsEntity not = list.get(i);
					not.setIsRead(true);
					userNoticationsRepo.save(list.get(i));
				}

			}
			return "done";

		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

}
