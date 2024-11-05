package com.PlayGroundAdv.Solar.repositories.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.users.UserCutomUpload;

public interface UserCutomUploadRepository extends JpaRepository<UserCutomUpload, Long> {
	List<UserCutomUpload> findByUserIdAndTitleNotIn(AuthentificationEntity user, List<String> customUploads);

	Boolean existsByUserIdAndTitle(AuthentificationEntity user, String title);

	List<UserCutomUpload> findByUserId(AuthentificationEntity user);
}
