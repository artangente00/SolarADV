package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.RoofAttachmentFavLibraryEntity;
import com.PlayGroundAdv.Solar.model.libraries.RoofAttachmentModel;

@Repository
public interface RoofAttachmentFavRepository extends JpaRepository<RoofAttachmentFavLibraryEntity,Long>{

	List<RoofAttachmentFavLibraryEntity> findByAuthentificationEntityId(Long idUser);
	List<RoofAttachmentFavLibraryEntity> findByAuthentificationEntityIdAndRoofAttachmentIdIn(Long idUser, List<Long> idRoof);
	RoofAttachmentFavLibraryEntity findByRoofAttachmentIdAndAuthentificationEntityId(Long idRoof, Long idUser);
	List<RoofAttachmentFavLibraryEntity> findByRoofAttachmentId(Long id);
	Boolean existsByAuthentificationEntityIdAndRoofAttachmentId(Long idUser, Long idRoof);
	
	@Query(value = "SELECT new  com.PlayGroundAdv.Solar.model.libraries.RoofAttachmentModel(u.roofAttachment.id, u.roofAttachment.manufacturer, u.roofAttachment.model)" + 
			" from RoofAttachmentFavLibraryEntity u where u.authentificationEntity.id = :p1")
	List<RoofAttachmentModel> findAllByUser(@Param("p1") Long idUser);

}
