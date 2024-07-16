package com.aluracursos.forohub.domain.topic;

import com.edu.LearningForum.dto.user.AccountDto;
import com.edu.LearningForum.entity.discussion.Category;
import jakarta.validation.constraints.NotNull;

public record DiscussionUpdateDto(
        @NotNull Long id,
        String subject,
        String content,
        AccountDto creator,
        Category category
) {
}