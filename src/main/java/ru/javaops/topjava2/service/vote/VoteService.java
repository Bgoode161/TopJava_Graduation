package ru.javaops.topjava2.service.vote;

import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.to.VoteTo;
import ru.javaops.topjava2.web.AuthUser;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    List<VoteTo> getAllByDate(LocalDate localDate);

    List<VoteTo> getAllByUser(AuthUser authUser);

    VoteTo registerVote(int restId, AuthUser authUser);

    void delete(int id);

}
