package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.ACDisconnectFavLibraryEntity;

@Repository
public interface ACDisconnectFavRepository extends JpaRepository<ACDisconnectFavLibraryEntity,Long>{
	ACDisconnectFavLibraryEntity findByAuthentificationEntityIdAndACDisconnectId(Long idUser, Long idACDisco);
	List<ACDisconnectFavLibraryEntity> findByAuthentificationEntityIdAndACDisconnectIdIn(Long idUser, List<Long> idACDisco);
	List<ACDisconnectFavLibraryEntity> findByACDisconnectId(Long id);
	Boolean existsByACDisconnectId(Long id);
	Boolean existsByAuthentificationEntityIdAndACDisconnectId(Long idUser, Long idACDisco);
	
	@Query("SELECT u.ACDisconnect.id from ACDisconnectFavLibraryEntity u WHERE u.authentificationEntity.id = :idUser ")
	List<Long> findFavoriteACD(@Param("idUser") Long idUser);
}
