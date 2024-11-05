package com.PlayGroundAdv.Solar.service.user_management;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PathEntity;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class LogoAndSignature {
	
	final CheckValueTypesService checkValue;
	final UserSettingRepository userSettingRepo;
	final PathRepository pathRepo;
	
	public LogoAndSignature(CheckValueTypesService checkValue, UserSettingRepository userSettingRepo,
			PathRepository pathRepo) {
		super();
		this.checkValue = checkValue;
		this.userSettingRepo = userSettingRepo;
		this.pathRepo = pathRepo;
	}

	public PathEntity getfilesPath() {
		try {
			return pathRepo.findAll().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public HashMap<String, List<String>> checkIamges(Long userId) {
		HashMap<String, List<String>> result = new HashMap<String, List<String>>();
		try {
			UserSettingEntity userSetting = userSettingRepo.findByUserIdId(userId);

			if (userSetting != null && userSetting.getSignature() != null) {
				List<String> list = new ArrayList<String>();
				list.add(userSetting.getSignature());
				list.add(userSetting.getSignatureConfirmed() != null ? userSetting.getSignatureConfirmed().toString()
						: null);
				result.put("Signature", list);
			}
			if (userSetting != null && userSetting.getCompanyLogoName() != null) {
				List<String> list = new ArrayList<String>();
				list.add(userSetting.getCompanyLogoName());
				list.add(userSetting.getLogoConfirmed() != null ? userSetting.getLogoConfirmed().toString() : null);
				result.put("Logo", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Delete User Logo
	public String deleteLogo(Long userID) throws IOException {
		try {
			String imgPath = getfilesPath().getDir();
			UserSettingEntity userSetting = userSettingRepo.findByUserIdId(userID);
			if (checkValue.isStringNotEmpty(userSetting.getCompanyLogoName())) {

				File logo = new File(imgPath + "img/logos/" + userSetting.getCompanyLogoName());
				String ext = FilenameUtils.getExtension(imgPath + "img/logos/" + userSetting.getCompanyLogoName());

				if (checkValue.Equals(ext, "svg")) {
					File logoMapping = new File(imgPath + "img/logos/Mapping/" + userSetting.getCompanyLogoName());
					if (logoMapping.exists())
						logoMapping.delete();
				}

				if (logo.exists())
					logo.delete();

				userSetting.setCompanyLogoName(null);
				userSetting.setLogoConfirmed(false);
				userSettingRepo.save(userSetting);
			}
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}

	}

	// Delete User Signature
	public String deleteSignature(Long userID) throws IOException {
		try {
			String imgPath = getfilesPath().getDir();
			UserSettingEntity userSetting = userSettingRepo.findByUserIdId(userID);
			if (checkValue.isStringNotEmpty(userSetting.getSignature())) {
				File sig = new File(imgPath + "img/signatures/" + userSetting.getSignature());
				String ext = FilenameUtils.getExtension(imgPath + "img/signatures/" + userSetting.getSignature());

				if (checkValue.Equals(ext, "svg")) {
					File sigMapping = new File(imgPath + "img/signatures/Mapping/" + userSetting.getSignature());
					if (sigMapping.exists())
						sigMapping.delete();
				}
				if (sig.exists())
					sig.delete();

				userSetting.setSignature(null);
				userSetting.setSignatureConfirmed(false);
				userSettingRepo.save(userSetting);

			}
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}

	}
}

