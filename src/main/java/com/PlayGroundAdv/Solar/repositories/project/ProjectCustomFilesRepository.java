package com.PlayGroundAdv.Solar.repositories.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.projects.ProjectCustomFiles;

public interface ProjectCustomFilesRepository extends JpaRepository<ProjectCustomFiles, Long> {

	ProjectCustomFiles findByProjectIdAndCustomUploadId(Long project, Long upload);

	List<ProjectCustomFiles> findByProjectId(Long project);

	Long deleteByCustomUploadId(Long upload);
	Long deleteByProjectId(Long projectId);	
}
