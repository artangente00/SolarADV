package com.PlayGroundAdv.Solar.service.project;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.repositories.PermitRepository;

@Service
@Transactional
public class ProjectStatusUpdate {

	final PermitRepository permitRepo;

	public ProjectStatusUpdate(PermitRepository permitRepo) {
		super();
		this.permitRepo = permitRepo;
	}

	@Scheduled(cron = "0 1 1 * * *")
	public void dailyCheckOnHoldProject() {
		try {
			permitRepo.deliverOnHoldProject(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
