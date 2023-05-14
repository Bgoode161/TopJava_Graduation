package ru.javaops.topjava2.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.service.vote.VoteService;
import ru.javaops.topjava2.to.VoteTo;
import ru.javaops.topjava2.web.AuthUser;

import java.util.List;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVoteController {

    @Autowired
    VoteService voteService;

    static final String REST_URL = "/api/user/votes";

    @GetMapping
    public List<VoteTo> getAll(@AuthenticationPrincipal AuthUser authUser) {
        return voteService.getAllByUser(authUser);
    }
}

