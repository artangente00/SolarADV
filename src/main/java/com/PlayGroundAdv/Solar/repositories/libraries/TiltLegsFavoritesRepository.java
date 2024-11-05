package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.TiltLegsFavLibraryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TiltLegsFavoritesRepository extends JpaRepository<TiltLegsFavLibraryEntity ,Long>{ 
	
	List<TiltLegsFavLibraryEntity> findAllByAuthentificationEntityId(Long id);
	List<TiltLegsFavLibraryEntity> findAllByAuthentificationEntityIdAndTiltLegsIdIn(Long id, List<Long>tiltLegsId);
	TiltLegsFavLibraryEntity findOneByAuthentificationEntityIdAndTiltLegsId(Long userId,Long tiltLegsId);
	List<TiltLegsFavLibraryEntity> findAllByTiltLegsId (Long tiltLegsId);
	Boolean existsByAuthentificationEntityIdAndTiltLegsId(Long userId,Long tiltLegsId);
}
