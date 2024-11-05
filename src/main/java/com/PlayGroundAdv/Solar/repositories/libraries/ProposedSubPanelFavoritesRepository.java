package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.ProposedSubPanelFavLibraryEntity;

@Repository
public interface ProposedSubPanelFavoritesRepository extends JpaRepository<ProposedSubPanelFavLibraryEntity ,Long>{ 
	
	List<ProposedSubPanelFavLibraryEntity> findAllByAuthentificationEntityId(Long id);
	List<ProposedSubPanelFavLibraryEntity> findAllByAuthentificationEntityIdAndProposedSubPanelIdIn(Long id, List<Long>proposedSubPanelId);
	ProposedSubPanelFavLibraryEntity findOneByAuthentificationEntityIdAndProposedSubPanelId(Long userId,Long proposedSubPanelId);
	List<ProposedSubPanelFavLibraryEntity> findAllByProposedSubPanelId (Long proposedSubPanelId);
	Boolean existsByAuthentificationEntityIdAndProposedSubPanelId(Long userId,Long proposedSubPanelId);
	
	@Query("SELECT u.proposedSubPanel.id from ProposedSubPanelFavLibraryEntity u WHERE u.authentificationEntity.id = :idUser ")
	List<Long> findFavoriteSubPanel(@Param("idUser") Long userId);
}


