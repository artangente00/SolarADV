package com.PlayGroundAdv.Solar.repositories.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.UtilsModel;

@Repository
public interface UserSettingRepository extends JpaRepository<UserSettingEntity ,Long>{
	UserSettingEntity findByUserIdId(Long userID);
	
	@Query("SELECT u.companyLogoName from UserSettingEntity u where u.userId.id =:p1")
	String findLogo(@Param("p1") Long id);
	
	@Query("SELECT u.signature from UserSettingEntity u where u.userId.id =:p1")
	String findSignature(@Param("p1") Long id);
	
	@Query("SELECT u.stanchionsType from UserSettingEntity u where u.userId.id =:p1")
	String findStanchionTypeByUserId(@Param("p1") Long id);
	
	@Query("SELECT u.microASystemSize from UserSettingEntity u where u.userId.id =:p1")
	String findMicroASystemSizeByUserId(@Param("p1") Long id);
	
	@Query("SELECT u.stringASystemSize from UserSettingEntity u where u.userId.id =:p1")
	String findStringASystemSizeByUserId(@Param("p1") Long id);
	
	@Query("SELECT u.wireTapSetting from UserSettingEntity u where u.userId.id =:p1")
	Boolean findWireTapSettingByUserId(@Param("p1") Long id);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.UtilsModel(u.userId.id,u.archiveDelay) from UserSettingEntity u where u.archiveAllowed = true and u.userId.email not like '%nuagetechnologies%' and u.userId.email not like '%arij%' and u.userId.email not like '%soumaya%' and u.userId.email not like '%imen%' and u.userId.email not like '%malek%' and u.userId.email not like '%nader%'")
	List<UtilsModel> findUserByArchiveAllowed();
}
