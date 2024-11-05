package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.DcCombinerorDiscFavoriteEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DcComDiscoFavoriteRepository extends JpaRepository<DcCombinerorDiscFavoriteEntity, Long> {

	List<DcCombinerorDiscFavoriteEntity> findByAuthentificationEntityIdAndDcCombinerDisconnectEntityIdIn(Long idUser, List<Long>idDcCombiner);
	Integer deleteByDcCombinerDisconnectEntityIdAndAuthentificationEntityId(Long idDcCombiner, Long idUser);
	List<DcCombinerorDiscFavoriteEntity> findByDcCombinerDisconnectEntityId(Long idDcCombiner);
	Boolean existsByAuthentificationEntityIdAndDcCombinerDisconnectEntityId(Long idUser, Long dcCombo);
	
	@Query("SELECT u.dcCombinerDisconnectEntity.id from DcCombinerorDiscFavoriteEntity u WHERE u.authentificationEntity.id = :idUser ")
	List<Long> findFavoriteDCD(@Param("idUser") Long idUser);
}
