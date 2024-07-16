package com.aluracursos.forohub.domain.comment;

import com.aluracursos.forohub.domain.user.AccountDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CommentDto(
        @NotBlank String content,
        @NotNull @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime timestamp,
        @NotNull Boolean isResolution,
        @NotNull @Valid AccountDto creator
) {
}
