package com.aluracursos.forohub.domain.topic;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record DiscussionSummaryDto(
        Long id,
        String subject,
        String content,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime creationTime,
        Boolean isActive,
        String creatorName,
        Category category
) {
    public DiscussionSummaryDto(Discussion discussion) {
        this(discussion.getId(), discussion.getSubject(), discussion.getContent(), discussion.getCreationTime(),
                discussion.getIsActive(), discussion.getCreator().getUsername(), discussion.getCategory());
    }
}