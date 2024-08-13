package com.sdp.taskandtimemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdp.taskandtimemanager.model.Projects;

public interface ProjectsRepo extends JpaRepository<Projects, Long> {

}
