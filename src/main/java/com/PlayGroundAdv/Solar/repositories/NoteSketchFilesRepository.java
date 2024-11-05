package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.NoteSketchFiles;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteSketchFilesRepository extends JpaRepository<NoteSketchFiles, Long> {

	List<NoteSketchFiles> findByPermitEntityIdAndFileType(Long permitId, int fileType);
	
	@Query("SELECT u.fileName from NoteSketchFiles u where u.permitEntity.id = :p1")
	List<String> getNoteSketchFiles(@Param("p1") Long projectId);

	@Query("SELECT u.fileName from NoteSketchFiles u where u.permitEntity.id = :p1 and u.fileType=10")
	List<String> getNoteSketchFilesOne(@Param("p1") Long projectId);

	@Query("SELECT u.fileName from NoteSketchFiles u where u.permitEntity.id = :p1 and u.fileType=20")
	List<String> getNoteSketchFilesTwo(@Param("p1") Long projectId);

	NoteSketchFiles findByPermitEntityIdAndFileName(Long idPermit, String fileName);
	@Query("SELECT u.filePath from NoteSketchFiles u where u.permitEntity.id = :p1 and u.fileName = :p2")
	String getFilePathByPermitEntityIdAndFileName(@Param("p1") Long idPermit, @Param("p2") String fileName);

	Long deleteByPermitEntityId(Long id);
}
