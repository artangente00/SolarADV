package com.PlayGroundAdv.Solar.repositories.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.model.LoginModel;
import com.PlayGroundAdv.Solar.model.LoginResult;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthentificationEntity, Long> {

	AuthentificationEntity findByIdNotAndEmailIgnoreCaseAndDeletedIsFalse(Long id, String email);

	AuthentificationEntity findByEmailIgnoreCaseAndDeletedIsFalse(String email);

	List<AuthentificationEntity> findByEmailContains(String email);
	
	Boolean findDeletedById(Long id);

	List<AuthentificationEntity> findByRoleEntityId(Long role);

	@Query("SELECT u.roleEntity.id FROM AuthentificationEntity u WHERE u.id = :p1 ")
	Long findRole(@Param("p1") Long userId);
	
	@Query("SELECT u.email FROM AuthentificationEntity u WHERE u.id = :p1 ")
	String findEmail(@Param("p1") Long userId);
	@Query("SELECT u.email FROM AuthentificationEntity u WHERE u.roleEntity.id = :p1  ")
	List<String> findEmailByRole(@Param("p1") Long roleId);

	
	//A.B this should be replaced with one param
	@Query("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult (   u.id,    u.firstName,  "
			+ "   u.lastName ) "
			+ " from AuthentificationEntity u WHERE ((:p1) IS NULL or u.id NOT IN (:p1)) and u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName")
	List<UsersEntityResult> findUserHaveNotFav(@Param("p1") List<Long> usersFavID, @Param("p2") Boolean deleted,
			@Param("p3") Boolean active, @Param("p4") Long idUserConnect);
	
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult (u.id, u.firstName, u.lastName ) "
			+ " from AuthentificationEntity u WHERE ((:p1) IS NULL or u.id NOT IN (:p1)) and u.deleted = false and u.active = true ORDER BY u.firstName")
	List<UsersEntityResult> findUserHaveNotFav(@Param("p1") List<Long> usersFavID);

	@Query("SELECT new com.PlayGroundAdv.Solar.model.LoginModel ( u.id,  u.firstName,  u.lastName,"
			+ " u.email, u.contractorCode, u.active, v.description, u.deleted, w.solarPermit,"
			+ " w.siteSurvey, w.hasSettingAccess, w.hasAhjMgtAccess, u.company ) "
			+ " from AuthentificationEntity u, RoleEntity v, UserSettingEntity w "
			+ " where lower(u.email) = lower(:p1) AND u.roleEntity = v.id AND w.userId.id = u.id AND u.deleted = false")
	LoginModel userInformations(@Param("p1") String email);

	// Return a list of deleted users + solarPermit and siteSurvey access
	@Query("SELECT new com.PlayGroundAdv.Solar.model.LoginResult (u.id, u.firstName, u.lastName, "
			+ " u.email, u.country, u.company, u.contractorCode, u.active, u.userLastLogin,"
			+ " u.roleEntity.description, v.solarPermit, v.siteSurvey) "
			+ " from AuthentificationEntity u, UserSettingEntity v where u.deleted is :p1 "
			+ " and v.userId.id = u.id ")
	List<LoginResult> ListOfDeletedUsers(@Param("p1") Boolean deleted);

	@Query("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult (u.id, u.firstName, u.lastName) "
			+ " from AuthentificationEntity u where u.deleted = :p1 AND u.active = :p2 "
			+ "AND u.email NOT LIKE '%nuagetechnologies-tn.com%' "
			+ "ORDER BY u.firstName")
	List<UsersEntityResult> allUsersByDeletedandActive(@Param("p1") Boolean delete, @Param("p2") Boolean active);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult (u.id, u.firstName, u.lastName) "
			+ " from AuthentificationEntity u where u.deleted = false ORDER BY u.firstName")
	List<UsersEntityResult> getNotDeletedUserName();
	
	@Query("SELECT u from AuthentificationEntity u where (u.roleEntity.id= 1 or u.roleEntity.id= 5)  and u.deleted=:p1 and u.active=:p2 and u.email LIKE '%nuagetechnologies-tn%' OR u.email LIKE '%nabil-g%'")
	List<AuthentificationEntity> getActivatedSpecial(@Param("p1") Boolean isDel, @Param("p2") Boolean isAct);
	
	@Query("SELECT u from AuthentificationEntity u where (u.roleEntity.id=1 or u.roleEntity.id= 5) and u.deleted=:p1 and u.active=:p2")
	List<AuthentificationEntity> getActivated(@Param("p1") Boolean isDel, @Param("p2") Boolean isAct);
	
	@Query("SELECT CONCAT(u.firstName,' ',u.lastName) from AuthentificationEntity u where u.id= :p1")
	String getUserName(@Param("p1") Long id);

}
