package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topic.*;
import com.aluracursos.forohub.domain.user.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.util.UriComponentsBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.net.URI;

@RestController
@RequestMapping("/discussions")
@SecurityRequirement(name = "jwt-auth")
public class DiscussionController {
    @Autowired
    private DiscussionRepository discussionRepository;
    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private AccountService accountService;

    @PostMapping
    @Transactional
    @Operation(summary = "Create a discussion", description = "The created discussion will be persisted in the database")
    public ResponseEntity<DiscussionSummaryDto> createDiscussion(@RequestBody @Valid DiscussionDto data,
                                                                 UriComponentsBuilder uriBuilder) {
        Discussion discussion = discussionRepository.save(new Discussion(data));
        accountService.hashPassword(discussion);
        DiscussionSummaryDto summaryDto = new DiscussionSummaryDto(discussion.getId(), discussion.getSubject(),
                discussion.getContent(), discussion.getCreationTime(), discussion.getIsActive(), discussion.getCreator().getUsername(),
                discussion.getCategory());
        URI location = uriBuilder.path("/discussions/{id}").buildAndExpand(discussion.getId()).toUri();
        return ResponseEntity.created(location).body(summaryDto);
    }

    @GetMapping
    @Operation(summary = "Retrieve discussions list",
            description = "Fetch discussions from the database, paginated with 2 discussions per page and sorted by creation time")
    public ResponseEntity<Page<DiscussionSummaryDto>> getDiscussions(
            @PageableDefault(size = 2, sort = "creationTime") Pageable pageRequest) {
        return ResponseEntity.ok(discussionRepository.findAll(pageRequest)
                .map(DiscussionSummaryDto::new));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a specific discussion", description = "Fetch a discussion by its ID")
    public ResponseEntity<DiscussionSummaryDto> getDiscussionById(@PathVariable Long id) {
        return ResponseEntity.ok(discussionService.getDiscussionById(id));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Modify a discussion", description = "Update the discussion specified by its ID")
    public ResponseEntity<DiscussionSummaryDto> updateDiscussion(@RequestBody @Valid DiscussionUpdateDto data,
                                                                 @PathVariable Long id) {
        return ResponseEntity.ok(discussionService.updateDiscussion(data, id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Remove a discussion", description = "Delete the discussion specified by its ID")
    public ResponseEntity removeDiscussion(@PathVariable Long id) {
        discussionService.deleteDiscussion(id);
        return ResponseEntity.noContent().build();
    }
}