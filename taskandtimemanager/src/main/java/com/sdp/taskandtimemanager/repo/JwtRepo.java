package com.sdp.taskandtimemanager.repo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sdp.taskandtimemanager.model.Token;

public interface JwtRepo extends JpaRepository<Token, String> {
    List<Token> findAllByUser_useridAndExpiredFalseAndRevokedFalse(Long userid);

    Optional<Token> findByToken(String token);
}
