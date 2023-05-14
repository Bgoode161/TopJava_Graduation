package ru.javaops.topjava2.util;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.to.VoteTo;

import java.util.List;

@UtilityClass
public class VoteUtil {

    public VoteTo createTo(Vote vote) {
        return new VoteTo(vote.id(), vote.getDateCreated(), vote.getUserId(),  vote.getRestaurant().id());
    }

    public List<VoteTo> getTos(List<Vote> votes) {
       return votes.stream().map(vote -> createTo(vote)).toList();
    }

}
