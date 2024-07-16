package com.aluracursos.forohub.domain.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscussionService {
    @Autowired
    private DiscussionRepository discussionRepository;

    public DiscussionSummaryDto getDiscussionById(Long id) {
        Optional<Discussion> discussionOpt = discussionRepository.findById(id);
        if (discussionOpt.isPresent()) {
            Discussion discussion = discussionOpt.get();
            return new DiscussionSummaryDto(discussion.getId(), discussion.getSubject(), discussion.getContent(),
                    discussion.getCreationTime(), discussion.getIsActive(), discussion.getCreator().getUsername(),
                    discussion.getCategory());
        }
        return null;
    }

    public DiscussionSummaryDto updateDiscussion(DiscussionUpdateDto data, Long id) {
        Optional<Discussion> discussionOpt = discussionRepository.findById(id);
        if (discussionOpt.isPresent()) {
            Discussion discussion = discussionOpt.get();
            discussion.updateInfo(data);
            return new DiscussionSummaryDto(discussion);
        }
        return null;
    }

    public void deleteDiscussion(Long id) {
        Optional<Discussion> discussionOpt = discussionRepository.findById(id);
        if (discussionOpt.isPresent()) {
            discussionRepository.deleteById(id);
        }
    }
}