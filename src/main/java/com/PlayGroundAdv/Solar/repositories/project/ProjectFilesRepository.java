package com.PlayGroundAdv.Solar.repositories.project;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.projects.ProjectFiles;

public interface ProjectFilesRepository extends JpaRepository<ProjectFiles, Long> {

	ProjectFiles findByProjectId(Long projectId);
	
	Long deleteByProjectId(Long projectId);	
}
