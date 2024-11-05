package com.PlayGroundAdv.Solar.service.sheets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.AccountBuildEntity;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.model.AccountBuildEntityModel;
import com.PlayGroundAdv.Solar.model.AccountBuildModel;
import com.PlayGroundAdv.Solar.model.libraries.SheetPageRequest;
import com.PlayGroundAdv.Solar.repositories.sheets.AccountBuildRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;

@Service
public class AccountBuildAssumptionsService {

	final NotificationEntityService notificationEntityService;
	final AccountBuildRepository accountBuildRepo;
	final AuthenticationRepository userRepo;

	public AccountBuildAssumptionsService(NotificationEntityService notificationEntityService,
			AccountBuildRepository accountBuildRepo, AuthenticationRepository userRepo) {
		super();
		this.notificationEntityService = notificationEntityService;
		this.accountBuildRepo = accountBuildRepo;
		this.userRepo = userRepo;
	}

	private Page<AccountBuildEntityModel> convertDto(Page<AccountBuildEntity> page, Boolean isDeleted, Pageable pageable) {
		try {
		return new PageImpl<>(
		page.stream().map(c -> new AccountBuildEntityModel(
				c.getId(),
				c.getPdfName(), 
				c.getAccountName(),
				c.getLastUpdate(),
				isDeleted,
				c.getAccount(),
				c.getAddedBy()!=null && Boolean.FALSE.equals(isDeleted)?c.getAddedBy().getFirstName()+ " " + c.getAddedBy().getLastName() 
						: c.getDeletedBy()!= null? c.getDeletedBy().getFirstName()+ " " + c.getDeletedBy().getLastName():"",
				c.getDeletedOn(),
				c.getUpdatedBy()!=null?c.getUpdatedBy().getFirstName() + " "+ c.getUpdatedBy().getLastName():""
				)).collect(Collectors.toList()),pageable, page.getTotalElements());

		} catch (Exception e) {
		e.printStackTrace();
		return null;
		}
	}
	
	public Page<AccountBuildEntityModel> filter(SheetPageRequest request) {

		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("pdfName"));
			
			String pdfName = request.getPdfName() != null ? request.getPdfName().toUpperCase()
					: "";
			String accountName = request.getAccountName() != null
					? request.getAccountName().toUpperCase()
					: "";
			
			if (pdfName == null && accountName == null && !Boolean.TRUE.equals(request.getIsDeleted())) {
				Page<AccountBuildEntity> p = accountBuildRepo.findByIsDeleted(request.getIsDeleted(), pageable);
				return convertDto(p,request.getIsDeleted(), pageable);
			} else {
				Page<AccountBuildEntity> p = accountBuildRepo.findAll(filter(pdfName, accountName, request.getIsDeleted()), pageable);
				return convertDto(p,request.getIsDeleted(), pageable);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Specification<AccountBuildEntity> filter(String pdfName, String accountName, Boolean deleted) {

		return new Specification<AccountBuildEntity>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<AccountBuildEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicatePdfName = pdfName != null ? cb.like(cb.lower(root.get("pdfName")), "%" + pdfName.toLowerCase() + "%"): null;
				Predicate predicateAccountName = accountName != null ? cb.like(cb.lower(root.get("accountName")), "%" + accountName.toLowerCase() + "%"): null;
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				List<Predicate> list = Arrays.asList(predicatePdfName, predicateAccountName, predicateDeleted);

				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}
	
	public String addAccountBuildSheet(AccountBuildModel newAccountBuildSheet, Long id) {

		try {
			AuthentificationEntity addedBy = userRepo.findById(id).orElse(new AuthentificationEntity());
			AuthentificationEntity account = userRepo.findById(Long.valueOf(newAccountBuildSheet.getAccountName()))
					.orElse(new AuthentificationEntity());

			AccountBuildEntity accountBuildSheet = new AccountBuildEntity();
			accountBuildSheet.setPdfName(newAccountBuildSheet.getPdfName());
			accountBuildSheet.setAccountName(account.getFirstName() + " " + account.getLastName());
			accountBuildSheet.setAccount(account.getId());
			accountBuildSheet.setLastUpdate(new Date());
			accountBuildSheet.setAddedBy(addedBy);
			accountBuildSheet.setIsDeleted(false);
			accountBuildRepo.save(accountBuildSheet);
			return accountBuildSheet.getId() + "";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}
	
	public String addAccountBuildLogic(MultipartFile file, String logicId)
			throws IOException {

		try {
			byte[] bytes5 = file.getBytes();
			new File(Constants.rapportPlansetFolderUrl + "AccountBuildSheets/").mkdirs();
			Path pathUp5 = Paths.get(Constants.rapportPlansetFolderUrl + "AccountBuildSheets/" + logicId + ".pdf");
			Files.write(pathUp5, bytes5);
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String editAccountBuildSheet(AccountBuildModel newAccountBuildSheet, Long id) {

		try {
			AuthentificationEntity addedBy = userRepo.findById(id).orElse(new AuthentificationEntity());
			AuthentificationEntity account = userRepo.findById(Long.valueOf(newAccountBuildSheet.getAccountName()))
					.orElse(new AuthentificationEntity());
			AccountBuildEntity accountBuildSheet = accountBuildRepo.findById(newAccountBuildSheet.getId())
					.orElse(new AccountBuildEntity());
			accountBuildSheet.setAccountName(account.getFirstName() + " " + account.getLastName());
			accountBuildSheet.setPdfName(newAccountBuildSheet.getPdfName());
			accountBuildSheet.setAccount(account.getId());
			accountBuildSheet.setLastUpdate(new Date());
			accountBuildRepo.save(accountBuildSheet);
			accountBuildSheet.setUpdatedBy(addedBy);
			String logicId = accountBuildSheet.getId() + "";
			notificationEntityService.addNewNotif(id, 0L, "Edit Account Build Sheet", "Libraries",
					"Edit Account Build Sheet",
					"The " + addedBy.getRoleEntity().getDescription() + " " + addedBy.getFirstName() + " "
							+ addedBy.getLastName() + " updated The Planset sheet  " + accountBuildSheet.getPdfName(),
					true);
			return logicId;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String deleteAccountBuildSheet(Long sheetID, Long userID) {
		try {
			AuthentificationEntity deletedBy = userRepo.findById(userID).orElse(new AuthentificationEntity());
			AccountBuildEntity accountBuildSheet = accountBuildRepo.findById(sheetID).orElse(new AccountBuildEntity());
			accountBuildSheet.setIsDeleted(true);
			accountBuildSheet.setDeletedBy(deletedBy);
			accountBuildSheet.setDeletedOn(new Date());
			accountBuildRepo.save(accountBuildSheet);
			notificationEntityService.addNewNotif(userID, 0L, "Delete Account Build assumption Sheet", "Libraries",
					"Delete Account Build assumption Sheet",
					"The " + deletedBy.getRoleEntity().getDescription() + " " + deletedBy.getFirstName() + " "
							+ deletedBy.getLastName() + " deleted The Planset PDF " + accountBuildSheet.getPdfName(),
					true);

			return "done";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}


	public String deletePermanetSheet(Long sheetID) {
		try {

			AccountBuildEntity accountBuildSheet = accountBuildRepo.findById(sheetID).orElse(new AccountBuildEntity());
			accountBuildRepo.delete(accountBuildSheet);
			File oldFile = new File(
					Constants.rapportPlansetFolderUrl + "AccountBuildSheets/" + accountBuildSheet.getId() + ".pdf");
			if (oldFile.exists()) {
				Path path = Paths.get(Constants.rapportPlansetFolderUrl + "AccountBuildSheets/"
						+ accountBuildSheet.getId() + ".pdf");
				Files.delete(path);
			}

			return "done";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}

	public String restoreAccountBuildsheet(Long sheetID) {

		try {
			AccountBuildEntity accountBuildSheet = accountBuildRepo.findById(sheetID).orElse(new AccountBuildEntity());
			accountBuildSheet.setIsDeleted(false);
			accountBuildRepo.save(accountBuildSheet);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "done";

	}
}
