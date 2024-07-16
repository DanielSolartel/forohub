package com.aluracursos.forohub.domain.topic;

import com.aluracursos.forohub.domain.comment.CommentDto;
import com.aluracursos.forohub.domain.user.AccountDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


public record DiscussionDto(
        @NotBlank String subject,
        @NotBlank String content,
        @NotNull @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime creationTime,
        Boolean isActive,
        @NotNull @Valid AccountDto creator,
        @NotNull Category category,
        @Valid List<CommentDto> comments
) {
}