package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.InverterLibraryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface InvertersFavoritesRepository extends JpaRepository<InverterLibraryEntity, Long> {

	InverterLibraryEntity findOneByAuthentificationEntityIdAndInvertersId(Long userId, Long inverterId);

	List<InverterLibraryEntity> findByAuthentificationEntityIdAndInvertersIdIn(Long userId, List<Long> inverterId);

	List<InverterLibraryEntity> findAllByInvertersId(Long inverterId);

	Boolean existsByAuthentificationEntityIdAndInvertersId(Long userId, Long inverterId);

	@Query("SELECT u.inverters.id from InverterLibraryEntity u WHERE u.authentificationEntity.id = :idUser ")
	List<Long> findFavoriteInverters(@Param("idUser") Long userId);

	@Query("SELECT u.authentificationEntity.id from InverterLibraryEntity u WHERE u.inverters.id = :idInverter ")
	List<Long> findFavoriteUsers(@Param("idInverter") Long inverterId);
}
