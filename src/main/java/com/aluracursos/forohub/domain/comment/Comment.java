package com.aluracursos.forohub.domain.comment;

import com.aluracursos.forohub.domain.topic.Discussion;
import com.aluracursos.forohub.domain.user.Account;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private LocalDateTime timestamp;
    private Boolean isResolution;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "discussion_id", referencedColumnName = "id")
    private Discussion discussion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private Account creator;

    public Comment(CommentDto data, Discussion discussion) {
        this.content = data.content();
        this.timestamp = data.timestamp();
        this.isResolution = data.isResolution();
        this.discussion = discussion;
        this.creator = new Account(data.creator());
    }
}