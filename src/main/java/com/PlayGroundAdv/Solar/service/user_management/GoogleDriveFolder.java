package com.PlayGroundAdv.Solar.service.user_management;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PathEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.model.EditUserInformations;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class GoogleDriveFolder {

	final CheckValueTypesService checkValue;
	final PermitRepository permitRepo;
	final PathRepository pathRepo;
	
	public GoogleDriveFolder(CheckValueTypesService checkValue, PermitRepository permitRepo,
			PathRepository pathRepo) {
		super();
		this.checkValue = checkValue;
		this.permitRepo = permitRepo;
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
	
	public void editGoogleDriveFolder(AuthentificationEntity user, EditUserInformations editUserInformations) {
		try {
		
			if ((user.getCompany() == null
					|| !checkValue.isStringNotEmpty(checkValue.removeSpecialChar(user.getCompany())))
					&& checkValue.isStringNotEmpty(
							checkValue.removeSpecialChar(editUserInformations.getCompany()))) {

				File sourceFile = new File(
						getfilesPath().getGoogleDriveFilePath() + checkValue.removeSpecialChar(user.getFirstName())
								+ " " + checkValue.removeSpecialChar(user.getLastName()));
				File destFile = new File(getfilesPath().getGoogleDriveFilePath()
						+ checkValue.removeSpecialChar(editUserInformations.getCompany()));
				moveFiles(sourceFile, destFile, editUserInformations.getId());

			} else if (checkValue.isStringNotEmpty(checkValue.removeSpecialChar(user.getCompany()))
					&& (editUserInformations.getCompany() == null || !checkValue
							.isStringNotEmpty(checkValue.removeSpecialChar(editUserInformations.getCompany())))) {
				File sourceFile = new File(
						getfilesPath().getGoogleDriveFilePath() + checkValue.removeSpecialChar(user.getCompany()));
				File destFile = new File(getfilesPath().getGoogleDriveFilePath()
						+ checkValue.removeSpecialChar(editUserInformations.getFirstName()) + " "
						+ checkValue.removeSpecialChar(editUserInformations.getLastName()));
				moveFiles(sourceFile, destFile, editUserInformations.getId());
			} else if (checkValue.isStringNotEmpty(checkValue.removeSpecialChar(user.getCompany()))
					&& checkValue.isStringNotEmpty(checkValue.removeSpecialChar(editUserInformations.getCompany()))
					&& checkValue.NotEquals(checkValue.removeSpecialChar(user.getCompany()),
							checkValue.removeSpecialChar(editUserInformations.getCompany()))) {
				File sourceFile = new File(
						getfilesPath().getGoogleDriveFilePath() + checkValue.removeSpecialChar(user.getCompany()));
				File destFile = new File(getfilesPath().getGoogleDriveFilePath()
						+ checkValue.removeSpecialChar(editUserInformations.getCompany()));
				moveFiles(sourceFile, destFile, editUserInformations.getId());
			} else if ((user.getCompany() == null
					|| !checkValue.isStringNotEmpty(checkValue.removeSpecialChar(user.getCompany())))
					&& (editUserInformations.getCompany() == null || !checkValue
							.isStringNotEmpty(checkValue.removeSpecialChar(editUserInformations.getCompany())))
					&& (checkValue.NotEquals(checkValue.removeSpecialChar(user.getFirstName()),
							checkValue.removeSpecialChar(editUserInformations.getFirstName()))
							|| checkValue.NotEquals(checkValue.removeSpecialChar(user.getLastName()),
									checkValue.removeSpecialChar(editUserInformations.getLastName())))) {
				File sourceFile = new File(
						getfilesPath().getGoogleDriveFilePath() + checkValue.removeSpecialChar(user.getFirstName())
								+ " " + checkValue.removeSpecialChar(user.getLastName()));
				File destFile = new File(getfilesPath().getGoogleDriveFilePath()
						+ checkValue.removeSpecialChar(editUserInformations.getFirstName()) + " "
						+ checkValue.removeSpecialChar(editUserInformations.getLastName()));
				moveFiles(sourceFile, destFile, editUserInformations.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// A.B 10-28 Rev 8 CR-2847 Move Project Folder on Company Change
	public void moveFiles(File sourceFile, File destFile, Long userID) {
		try {
			List<PermitEntity> listProject = permitRepo.findByAuthentificationEntityId(userID);
			if (!listProject.isEmpty()) {
				for (int i = 0; i < listProject.size(); i++) {

					String folderName = listProject.get(i).getProjectName();
					if (!checkValue.isStringNotEmpty(listProject.get(i).getProjectName()))
						folderName = listProject.get(i).getHomeOwnLastName() + ", "
								+ listProject.get(i).getHomeOwnName();
					if (new File(sourceFile.getPath() + "/" + folderName).exists()) {

						if (!destFile.exists())
							destFile.mkdir();

						File source = new File(sourceFile.getPath() + "/" + folderName);
						File dest = new File(destFile.getPath() + "/" + folderName);
						source.renameTo(dest);
					}
				}
			}
			if (sourceFile.exists()) {
				File[] listOfFiles = sourceFile.listFiles();
				if (listOfFiles.length == 0)
					sourceFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String getfolderName(AuthentificationEntity user) {
		String company = checkValue.removeSpecialChar(user.getCompany());
		return checkValue.isStringNotEmpty(company) ? company
				: checkValue.removeSpecialChar(user.getFirstName()) + " "
						+ checkValue.removeSpecialChar(user.getLastName());
	}
	
	public String getProjectName(PermitEntity permit) {
		String projectName = checkValue.removeSpecialChar(permit.getProjectName());
		return checkValue.isStringNotEmpty(projectName) ? projectName
				: checkValue.removeSpecialChar(permit.getHomeOwnLastName()) + ", "
						+ checkValue.removeSpecialChar(permit.getHomeOwnName());
	}
}

