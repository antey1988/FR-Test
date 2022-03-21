package com.antey.fr.repo;

import com.antey.fr.model.UserAnswer;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepo extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> findAllByUser(Long userId);
}
