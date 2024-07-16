package com.aluracursos.forohub.domain.user;

import com.aluracursos.forohub.domain.topic.Discussion;
import com.aluracursos.forohub.domain.topic.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DiscussionRepository discussionRepository;

    public void hashPassword(Discussion discussion) {
        discussion.getCreator().setPassword(passwordEncoder.encode(discussion.getCreator().getPassword()));
        discussion.getComments()
                .forEach(c -> c.getCreator().setPassword(passwordEncoder.encode(c.getCreator().getPassword())));
        accountRepository.save(discussion.getCreator());
        discussionRepository.save(discussion);
    }
}