package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ACCombinerFavLibraryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AcCombinerSLCsFavoritesRepository extends JpaRepository<ACCombinerFavLibraryEntity ,Long>{

	List<ACCombinerFavLibraryEntity> findByAuthentificationEntityId(Long idUser);
	List<ACCombinerFavLibraryEntity> findByAuthentificationEntityIdAndAcCombinerSlcIdIn(Long idUser, List<Long> acCombinerDiscoID);
	ACCombinerFavLibraryEntity findByAcCombinerSlcIdAndAuthentificationEntityId(Long acCombinerDiscoID, Long idUser);
	List<ACCombinerFavLibraryEntity> findByAcCombinerSlcId(Long acCombinerDiscoID);
	Boolean existsByAcCombinerSlcIdAndAuthentificationEntityId(Long acCombinerDiscoID, Long idUser);

}
