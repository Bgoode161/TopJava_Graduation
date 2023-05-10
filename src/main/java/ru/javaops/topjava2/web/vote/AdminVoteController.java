package ru.javaops.topjava2.web.vote;

import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.model.Vote;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteController extends AbstractVoteController {

    static final String REST_URL = "/api/admin/votes";

    @GetMapping
    public List<Vote> getAllByDate(@RequestParam("date_created")  LocalDate dateCreated) {
       return voteRepository.findAllByDateCreated(dateCreated, Sort.by(Sort.Direction.DESC, "timeCreated"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        voteRepository.deleteExisted(id);
    }

}
