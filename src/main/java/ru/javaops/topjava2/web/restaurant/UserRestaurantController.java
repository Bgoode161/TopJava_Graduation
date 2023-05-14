package ru.javaops.topjava2.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.to.VoteTo;
import ru.javaops.topjava2.web.AuthUser;

import java.net.URI;
import java.util.List;
@Slf4j
@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/api/user/restaurants";

    static final String VOTES_URL = "/api/profile/my_votes";

    @GetMapping("/{id}")
    @Override
    public Restaurant get(@PathVariable int id) {
        log.info("get restaurant id={} without dishes", id);
        return super.get(id);
    }

    @Override
    @GetMapping("/{id}/actual_menu")
    public Restaurant getWithMenu(@PathVariable int id) {
        log.info("get restaurant id={} with actual menu", id);
        return super.getWithMenu(id);
    }

    @GetMapping("/actual")
    @Override
    public List<Restaurant> getAllActual() {
        log.info("get all restaurants with actual menu");
        return super.getAllActual();
    }

    @PostMapping("{id}/register_vote")
    public ResponseEntity<VoteTo> registerVote(@PathVariable int id , @AuthenticationPrincipal AuthUser authUser) {
        log.info("voting for restaurant id={}, user id ={}", id, authUser.id());
        VoteTo newVote =  voteService.registerVote(id, authUser);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(VOTES_URL + "/{id}").buildAndExpand(id, newVote.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(newVote);
    }
}
