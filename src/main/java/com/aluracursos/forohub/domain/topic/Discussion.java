package com.aluracursos.forohub.domain.topic;

import com.aluracursos.forohub.domain.comment.Comment;
import com.aluracursos.forohub.domain.user.Account;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "discussions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String subject;

    @Column(unique = true)
    private String content;

    private LocalDateTime creationTime;
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private Account creator;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    public Discussion(DiscussionDto data) {
        this.subject = data.subject();
        this.content = data.content();
        this.creationTime = data.creationTime();
        this.isActive = true;
        this.creator = new Account(data.creator());
        this.category = data.category();
        this.comments = data.comments().stream()
                .map(c -> new Comment(c, this))
                .collect(Collectors.toList());
    }

    public void updateInfo(DiscussionUpdateDto data) {
        if (data.subject() != null) {
            this.subject = data.subject();
        }
        if (data.content() != null) {
            this.content = data.content();
        }
        if (data.creator() != null) {
            this.creator = creator.updateCreator(data.creator());
        }
        if (data.category() != null) {
            this.category = data.category();
        }
    }
}