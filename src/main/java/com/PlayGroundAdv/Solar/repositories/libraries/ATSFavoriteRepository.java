package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.libraries.ATSFavorite;
@Repository
public interface ATSFavoriteRepository extends JpaRepository<ATSFavorite ,Long> {

	ATSFavorite findByIdAtsIdAndIdUserId(Long comp, Long user);
	List<ATSFavorite> findByIdAtsIdInAndIdUserId(List<Long> comp, Long user);
	Boolean existsByIdAtsIdAndIdUserId(Long comp, Long user);
	
	@Query("SELECT u.idUser.id from ATSFavorite u WHERE u.idAts.id = :p1 ")
	List<Long> findFavoriteUserId(@Param("p1") Long id);
}
