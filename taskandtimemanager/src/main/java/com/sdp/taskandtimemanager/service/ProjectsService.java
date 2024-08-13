package com.sdp.taskandtimemanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.sdp.taskandtimemanager.model.Projects;
import com.sdp.taskandtimemanager.model.Users;
import com.sdp.taskandtimemanager.repo.ProjectsRepo;
import com.sdp.taskandtimemanager.repo.UsersRepo;

@Service
public class ProjectsService {

    @Autowired
    private ProjectsRepo repo;

    @Autowired
    private UsersRepo urepo;

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername(); // Return email or username
        }
        throw new IllegalStateException("No authenticated user found");
    }

    public List<Projects> findAllProjects() {
        return repo.findAll();
    }

    public Projects findProjectById(Long projectId) {
        return repo.findById(projectId).orElse(null);
    }

    public String addProject(Projects project) {
        String currentUsername = getCurrentUsername();
        Users user = urepo.findManagerByEmail(currentUsername); // Assuming you have a method to find user by email
        if (user == null) {
            return "User not found";
        }
        project.setManager(user);
        repo.save(project);
        return "Project Added successfully";
    }

    public Projects updateProject(Long projectId, Projects project) {
        Optional<Projects> optionalProjects = repo.findById(projectId);
        if (optionalProjects.isPresent()) {
            Projects existingProjects = optionalProjects.get();
            existingProjects.setProjectname(project.getProjectname());
            existingProjects.setProjectdescription(project.getProjectdescription());
            existingProjects.setDuedate(project.getDuedate());
            existingProjects.setManager(project.getManager());
            return repo.save(existingProjects);
        }
        return project;
    }

    public void deleteProject(Long projectId) {
        repo.deleteById(projectId);
    }

    public Projects patchProject(Long projectId, Projects project) {
        Optional<Projects> optionalProjects = repo.findById(projectId);
        if (optionalProjects.isPresent()) {
            Projects existingProject = optionalProjects.get();

            if (project.getProjectname() != null) {
                existingProject.setProjectname(project.getProjectname());
            }

            if (project.getProjectdescription() != null) {
                existingProject.setProjectdescription(project.getProjectdescription());
            }

            if (project.getDuedate() != null) {
                existingProject.setDuedate((project.getDuedate()));
            }

            if (project.getManager() != null) {
                existingProject.setManager(project.getManager());
            }

            return repo.save(existingProject);
        }
        return project;
    }

}