package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.LeasePPAMeterFavLibraryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LeasePPAMeterFavoritesRepository extends JpaRepository<LeasePPAMeterFavLibraryEntity ,Long>{ 
	
	List<LeasePPAMeterFavLibraryEntity> findAllByAuthentificationEntityId(Long id);
	List<LeasePPAMeterFavLibraryEntity> findByAuthentificationEntityIdAndLeasePPAMeterIdIn(Long id, List<Long>ppaMeterId);
	LeasePPAMeterFavLibraryEntity findOneByAuthentificationEntityIdAndLeasePPAMeterId(Long userId,Long ppaMeterId);
	List<LeasePPAMeterFavLibraryEntity> findAllByLeasePPAMeterId (Long ppaMeterId);
	Boolean existsByAuthentificationEntityIdAndLeasePPAMeterId(Long userId,Long ppaMeterId);
	
	@Query("SELECT u.leasePPAMeter.id from LeasePPAMeterFavLibraryEntity u WHERE u.authentificationEntity.id = :idUser ")
	List<Long> findFavoriteLeasePPAMeters(@Param("idUser") Long userId);
}
