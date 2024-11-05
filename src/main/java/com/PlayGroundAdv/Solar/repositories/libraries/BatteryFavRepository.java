package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.BatteryFavLibraryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryFavRepository extends JpaRepository<BatteryFavLibraryEntity,Long>{

	List<BatteryFavLibraryEntity> findByAuthentificationEntityIdAndBatteryIdIn(Long idUser, List<Long>idBattery);
	List<BatteryFavLibraryEntity> findByBatteryId(Long idBattery);
	BatteryFavLibraryEntity  findByAuthentificationEntityIdAndBatteryId(Long idUser, Long idBattery);
	Boolean existsByAuthentificationEntityIdAndBatteryId(Long idUser, Long idBattery);
	
	@Query("SELECT u.battery.id from BatteryFavLibraryEntity u WHERE u.authentificationEntity.id = :idUser ")
	List<Long> findFavoriteBatteries(@Param("idUser") Long idUser);
}
