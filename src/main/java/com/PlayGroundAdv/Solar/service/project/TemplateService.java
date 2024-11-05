package com.PlayGroundAdv.Solar.service.project;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.TemplateModelResponse;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;

@Service
@Transactional
public class TemplateService {

	final PermitRepository permitRepo;
	final HistoryActivityService historyActivityService;
	final AuthenticationRepository userRepo;

	public TemplateService(HistoryActivityService historyActivityService, PermitRepository permitRepo,
			AuthenticationRepository userRepo) {
		super();
		this.historyActivityService = historyActivityService;
		this.permitRepo = permitRepo;
		this.userRepo = userRepo;
	}

	public List<TemplateModelResponse> getAllTemplate() {
		try {
			return permitRepo.getAllTemplates();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	public List<TemplateModelResponse> getAllTemplateByUser(Long idUser) {
		try {
			AuthentificationEntity findUser = userRepo.findById(idUser)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idUser));

			return permitRepo.getAllTemplatesByUser(idUser,
					findUser.getCompany() != null ? findUser.getCompany().trim().toUpperCase() : null);

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

}
