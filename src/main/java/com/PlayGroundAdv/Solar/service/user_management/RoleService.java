package com.PlayGroundAdv.Solar.service.user_management;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.RoleEntity;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.ChangeRoleModel;
import com.PlayGroundAdv.Solar.repositories.RoleRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;

@Service
@Transactional
public class RoleService {

	final UserSettingRepository userSettingRepo;
	final AuthenticationRepository userRepo;
	final RoleRepository roleRepo;

	public RoleService(UserSettingRepository userSettingRepo, AuthenticationRepository userRepo,
			RoleRepository roleRepo) {
		super();
		this.userSettingRepo = userSettingRepo;
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}

	/*
	 * editUserRole
	 */
	public String ediUsertRole(ChangeRoleModel changeRoleModel) {

		try {
			AuthentificationEntity user = userRepo.findById(changeRoleModel.getIdUser()).orElse(null);
			RoleEntity role = roleRepo.findById(changeRoleModel.getIdRole()).orElse(null);
			if (user != null && role != null) {
				UserSettingEntity userSetting = userSettingRepo.findByUserIdId(user.getId());
				user.setRoleEntity(role);
				userSetting.setHasSettingAccess(Boolean.TRUE.equals(changeRoleModel.getHasSettingAccess()));
				userSetting.setHasAhjMgtAccess(Boolean.TRUE.equals(changeRoleModel.getHasAhjMgtAccess()) && role.getId() == 1);
				userSetting.setHasLibVerifAccess(Boolean.TRUE.equals(changeRoleModel.getHasLibVerifAccess()) && role.getId() == 1);
				userSettingRepo.save(userSetting);
				userRepo.save(user);
				return "role updated successfuly";
			} else {
				return "role not found";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

}
