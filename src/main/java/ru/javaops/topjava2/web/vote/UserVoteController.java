package ru.javaops.topjava2.web.vote;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.error.IllegalRequestDataException;
import ru.javaops.topjava2.error.NotFoundException;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.web.AuthUser;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVoteController extends AbstractVoteController {

    static final String REST_URL = "/api/profile/my_votes";

    @GetMapping
    public List<Vote> getAll(@AuthenticationPrincipal AuthUser authUser) {
        return voteRepository.findByUserId(authUser.id(), Sort.by(Sort.Direction.DESC, "dateCreated"));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @AuthenticationPrincipal AuthUser authUser) throws AccessDeniedException {
        Vote vote = voteRepository.getExisted(id);
        if (!vote.getUserId().equals(authUser.getUser().id())) {
            throw new AccessDeniedException("You don't have permission for this operation");
        }
        if (vote.getDateCreated().equals(LocalDate.now()) && LocalTime.now().isAfter(LocalTime.of(11, 00))) {
            throw new IllegalRequestDataException("You cannot change your decision after 11:00 AM");
        }
        voteRepository.deleteExisted(id);
    }

}

