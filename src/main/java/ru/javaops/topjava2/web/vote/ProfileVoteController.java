package ru.javaops.topjava2.web.vote;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.error.DataConflictException;
import ru.javaops.topjava2.error.IllegalRequestDataException;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.web.AuthUser;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = ProfileVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileVoteController extends AdminVoteController{

    static final String REST_URL = "/api/profile/votes";


    @PostMapping("/make_a_vote")
    public ResponseEntity<Vote> registerVote(@RequestParam(name = "restaurant_id") Integer restaurantId , @AuthenticationPrincipal AuthUser authUser) {
        Vote newVote = new Vote();
        newVote.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        newVote.setLocalDate(LocalDate.now());
        newVote.setLocalTime(LocalTime.now());
        newVote.setUserId(authUser.getUser().id());
        Optional<Vote> voteDB = voteRepository.findByDateAndUser(newVote.getLocalDate(), authUser.id());
        if (voteDB.isPresent()) {
            LocalTime controlTime = LocalTime.of(11, 00);
            LocalTime newVoteTime = newVote.getLocalTime();
            if (newVoteTime.isAfter(controlTime)) {
                throw new IllegalRequestDataException("You cannot change your vote after 11:00 AM");
            }
        }
          newVote =  voteRepository.save(newVote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}").buildAndExpand(newVote.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(newVote);
    }

    @Override
    @GetMapping("/winner")
    public List<Integer> getWinner() {
        return super.getWinner();
    }
}

