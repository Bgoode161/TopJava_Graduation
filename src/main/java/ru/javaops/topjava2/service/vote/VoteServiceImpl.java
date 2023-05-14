package ru.javaops.topjava2.service.vote;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.error.IllegalRequestDataException;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.repository.VoteRepository;
import ru.javaops.topjava2.to.VoteTo;
import ru.javaops.topjava2.util.VoteUtil;
import ru.javaops.topjava2.web.AuthUser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final LocalTime CONTROL_TIME = LocalTime.of(11, 00);

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    VoteRepository voteRepository;


    @Override
    @Transactional
    public List<VoteTo> getAllByDate(LocalDate dateCreated) {
        return VoteUtil.getTos(voteRepository.findAllByDateCreated(dateCreated, Sort.by(Sort.Direction.DESC, "timeCreated")));
    }


    @Override
    @Transactional
    public List<VoteTo> getAllByUser(AuthUser authUser) {
        return VoteUtil.getTos(voteRepository.findByUserId(authUser.id(), Sort.by(Sort.Direction.DESC, "dateCreated")));
    }

    @Override
    @Transactional
    public VoteTo registerVote(int restId, AuthUser authUser) {
        Vote newVote = new Vote();
        newVote.setRestaurant(restaurantRepository.getReferenceById(restId));
        newVote.setDateCreated(LocalDate.now());
        newVote.setTimeCreated(LocalTime.now());
        newVote.setUserId(authUser.getUser().id());
       int id = getExistedId(newVote.getDateCreated(), authUser.id());
       if (id != 0 && LocalTime.now().isBefore(CONTROL_TIME)) {
           newVote.setId(id);
       }
       else if (id != 0 && LocalTime.now().isAfter(CONTROL_TIME)) {
           throw new IllegalRequestDataException("You cannot change your vote after 11:00 AM");
        }
        return VoteUtil.createTo(voteRepository.save(newVote));
    }

    @Override
    public void delete(int id) {
        voteRepository.deleteExisted(id);
    }

    public int getExistedId(LocalDate dateCreated, int userId) {
        Optional<Vote> voteDB = voteRepository.findVoteByDateCreatedAndAndUserId(dateCreated, userId);
        int voteId = 0;
        if (voteDB.isPresent()) {
          voteId = voteDB.get().id();
        }
        return voteId;
    }
}