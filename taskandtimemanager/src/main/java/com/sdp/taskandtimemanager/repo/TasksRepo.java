package com.sdp.taskandtimemanager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdp.taskandtimemanager.model.Tasks;

public interface TasksRepo extends JpaRepository<Tasks, Long> {
    List<Tasks> findTasksByMemberUserid(Long userId);

    List<Tasks> findByMemberUserid(Long userId);

    // Tasks findTasksByUserId(Long userId);
}