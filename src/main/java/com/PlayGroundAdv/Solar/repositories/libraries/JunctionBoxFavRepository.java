package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.JunctionBoxFavLibraryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface JunctionBoxFavRepository extends JpaRepository<JunctionBoxFavLibraryEntity,Long>{

	List<JunctionBoxFavLibraryEntity> findByAuthentificationEntityId(Long idUser);
	List<JunctionBoxFavLibraryEntity> findByAuthentificationEntityIdAndJboxIdIn(Long idUser, List<Long>idJbox);
	List<JunctionBoxFavLibraryEntity> findByJboxId(Long idJbox);
	Boolean existsByJboxIdAndAuthentificationEntityId(Long railRackingId, Long userId);
	JunctionBoxFavLibraryEntity findByJboxIdAndAuthentificationEntityId(Long idJbox, Long idUser);
	
	@Query("SELECT u.jbox.id from JunctionBoxFavLibraryEntity u WHERE u.authentificationEntity.id = :idUser ")
	List<Long> findByUserId(@Param("idUser") Long idUser);
	
}
