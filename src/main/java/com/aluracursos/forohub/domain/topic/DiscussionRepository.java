package com.aluracursos.forohub.domain.topic;

import com.edu.LearningForum.entity.discussion.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
}